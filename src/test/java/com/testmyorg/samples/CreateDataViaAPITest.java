package com.testmyorg.samples;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.testmy.data.matchers.Matchers.hasField;
import static org.testmy.data.matchers.Matchers.hasName;
import static org.testmy.data.matchers.Matchers.hasRecordTypeName;
import static org.testmy.data.matchers.Matchers.ofShape;
import static org.testmy.data.matchers.ObjectMatchers.account;
import static org.testmy.data.matchers.ObjectMatchers.opportunity;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testmy.data.matchers.HasFields;
import org.testmy.screenplay.ability.InitializeFramework;
import org.testmy.screenplay.act.CleanData;
import org.testmy.screenplay.act.CreateData;
import org.testmy.screenplay.act.Initialize;
import org.testmy.screenplay.factory.Login;
import org.testmy.screenplay.factory.ability.Authenticate;
import org.testmy.screenplay.factory.ability.Call;
import org.testmy.screenplay.question.QueryData;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;

@RunWith(SerenityRunner.class)
public class CreateDataViaAPITest {
    Actor admin = Actor.named("Andim");
    Actor jane = Actor.named("Jane");

    @Before
    public void before() {
        givenThat(admin)
                .can(Authenticate.as("Admin"))
                .can(Call.partnerApi())
                .can(InitializeFramework.withMetadata())
                .wasAbleTo(Login.viaAPI());

        givenThat(jane)
                .can(Authenticate.as("Sales Manager"))
                .can(Call.partnerApi())
                .wasAbleTo(Login.viaAPI());
    }

    @After
    public void after() {
        admin.attemptsTo(CleanData.afterTest());
    }

    @Test
    public void createData_SimpleRecord() {
        final String accountName = "Test Client:#" + UUID.randomUUID().toString();
        final HasFields accountShape = ofShape(
                account(),
                hasName(accountName));

        when(jane).attemptsTo(CreateData.record(accountShape));

        then(jane).should(seeThat(QueryData.similarTo(accountShape), is(notNullValue())));
    }

    @Test
    public void createData_WithDateAndRecordType() {
        givenThat(admin).wasAbleTo(Initialize.framework());

        final Calendar calendarForDate = Calendar.getInstance();
        calendarForDate.add(Calendar.DAY_OF_YEAR, 10);
        final Date date = calendarForDate.getTime();

        final HasFields opportunityWithRecordType = ofShape(
                opportunity(),
                hasName("Test Opportunity"),
                hasField("StageName", "TODO:"),
                hasField("CloseDate", date),
                hasRecordTypeName("Utility_Opportunity"));
        when(jane).attemptsTo(CreateData.record(opportunityWithRecordType));

        then(jane).should(seeThat(QueryData.similarTo(opportunityWithRecordType), is(notNullValue())));
    }
}
