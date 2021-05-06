package com.testmyorg.samples;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testmy.screenplay.act.Ensure;
import org.testmy.screenplay.fact.PersonaBehaviour;
import org.testmy.screenplay.factory.Login;
import org.testmy.screenplay.factory.ability.Authenticate;
import org.testmy.screenplay.factory.ability.Call;
import org.testmy.screenplay.question.PartnerConnection;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)
public class LoginTest {
    @Managed
    WebDriver browser;
    Actor mike;

    @Before
    public void before() {
        mike = Actor.named("Mike");
        mike.has(PersonaBehaviour.of("Admin"));

        givenThat(mike)
                .can(Authenticate.withCredentials())
                .can(BrowseTheWeb.with(browser));
    }

    @Test
    public void authenticateViaLoginForm() {
        when(mike).attemptsTo(Login.viaUI());

        then(mike).should(seeThat(TheWebPage.title(), allOf(containsString("Home"), containsString("Salesforce"))));
    }

    @Test
    public void authenticateUsingSessionIdObtainedViaAPI() {
        givenThat(mike)
                .can(Call.partnerApi());

        when(mike).attemptsTo(Login.usingCookies());

        then(mike).should(seeThat(TheWebPage.title(), allOf(containsString("Home"), containsString("Salesforce"))));
    }

    @Test
    public void authenticateWithSessionIdThroughFrontDoor() {
        givenThat(mike)
                .can(Call.partnerApi());

        when(mike).attemptsTo(Login.viaFrontDoorUrl());

        then(mike).should(seeThat(TheWebPage.title(), allOf(containsString("Home"), containsString("Salesforce"))));
    }
    @Test
    public void authenticateViaAPI() {
        final Actor mike = Actor.named("Mike");
        mike.has(PersonaBehaviour.of("Admin"));

        givenThat(mike)
                .can(Authenticate.withCredentials())
                .can(Call.partnerApi());

        when(mike).attemptsTo(Ensure.partnerConnection());

        then(mike).should(seeThat(PartnerConnection.sessionId(), is(notNullValue())));
    }    
}
