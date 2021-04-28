package com.testmyorg.samples.login;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.testmy.data.matchers.Matchers.account;
import static org.testmy.data.matchers.Matchers.hasName;
import static org.testmy.data.matchers.Matchers.ofShape;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testmy.data.matchers.HasFields;
import org.testmy.screenplay.ability.Authenticate;
import org.testmy.screenplay.ability.CallPartnerSoapApi;
import org.testmy.screenplay.fact.PersonaBehaviour;
import org.testmy.screenplay.question.QueryData;
import org.testmy.screenplay.task.CleanData;
import org.testmy.screenplay.task.CreateData;
import org.testmy.screenplay.task.Login;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;

@RunWith(SerenityRunner.class)
public class CreateDataViaAPITest {
    Actor admin = Actor.named("Andim");

    @Before
    public void before() {
        admin.has(PersonaBehaviour.of("Admin"));
        admin.can(Authenticate.withCredentials())
                .can(CallPartnerSoapApi.ofVersion("51"))
                .wasAbleTo(Login.viaSoapApi());
    }

    @After
    public void after() {
        admin.attemptsTo(CleanData.afterTest());
    }

    @Test
    public void createData() {
        final String accountName = "Test Client:#" + UUID.randomUUID().toString();
        final Actor jane = Actor.named("Jane");
        jane.has(PersonaBehaviour.of("Sales Manager"));

        givenThat(jane)
                .can(Authenticate.withCredentials())
                .can(CallPartnerSoapApi.ofVersion("51"))
                .wasAbleTo(Login.viaSoapApi());

        final HasFields accountShape = ofShape(
                account(),
                hasName(accountName));
        when(jane).attemptsTo(CreateData.record(accountShape));

        then(jane).should(seeThat(QueryData.similarTo(accountShape), is(notNullValue())));
    }
}
