package com.testmyorg.samples.data;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.testmy.data.matchers.Matchers.hasField;
import static org.testmy.data.matchers.Matchers.hasId;
import static org.testmy.data.matchers.Matchers.ofShape;
import static org.testmy.data.matchers.ObjectMatchers.contact;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testmy.config.Config;
import org.testmy.data.matchers.HasFields;
import org.testmy.screenplay.act.CleanData;
import org.testmy.screenplay.act.task.CreateRecord;
import org.testmy.screenplay.factory.Login;
import org.testmy.screenplay.factory.ability.Authenticate;
import org.testmy.screenplay.factory.ability.Call;
import org.testmy.screenplay.question.QueryData;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)
public class GlobalActionTest {
    @Managed
    WebDriver browser;

    private Actor admin = Actor.named("Adnim");

    @Before
    public void before() {
        givenThat(admin)
                .can(Authenticate.as("Admin"))
                .can(Call.partnerApi())
                .can(BrowseTheWeb.with(browser))
                .wasAbleTo(Login.viaAPI());
        admin.wasAbleTo(Login.withSessionIdInFrontDoorUrl());
    }

    @After
    public void after() {
        admin.attemptsTo(CleanData.afterTest());
    }

    @Test
    public void createContactViaGlobalAction() {
        final HasFields contactShape = ofShape(
                hasField("LastName", "Test User Last Name"),
                hasField("FirstName", "Test User First Name"),
                hasField("Email", "test@email.com"),
                hasField("Phone", "+1234567890"),
                contact());

        when(admin).attemptsTo(CreateRecord.viaGlobalAction(Config.GLOBAL_ACTION_NEW_CONTACT).of(contactShape));

        then(admin).should(seeThat(QueryData.similarTo(contactShape), hasId()));
    }
}
