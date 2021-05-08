package com.testmyorg.samples;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
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
public class LoginIT {
    @Managed
    WebDriver browser;
    Actor mike;

    @Before
    public void before() {
        mike = Actor.named("Mike");
        mike.has(PersonaBehaviour.of("Sales Manager"));

        givenThat(mike)
                .can(Authenticate.withCredentials())
                .can(Call.partnerApi())
                .can(BrowseTheWeb.with(browser));
    }

    @Test
    public void authenticateViaLoginForm() {
        when(mike).attemptsTo(Login.viaForm());

        assertLoggedIn(mike);
    }

    @Test
    public void authenticateWithCredentialsInLoginUrl() {
        when(mike).attemptsTo(Login.withCredentialsInUrl());

        assertLoggedIn(mike);
    }

    @Test
    public void authenticateWithSessionIdInFrontDoorUrl() {
        when(mike).attemptsTo(Login.withSessionIdInFrontDoorUrl());

        assertLoggedIn(mike);
    }

    @Test
    public void authenticateUsingSessionIdAsCookies() {
        when(mike).attemptsTo(Login.withSessionIdAsCookies());

        assertLoggedIn(mike);
    }

    @Test
    public void authenticateViaAPI() {
        when(mike).attemptsTo(Login.viaAPI());

        then(mike).should(seeThat(PartnerConnection.sessionId(), is(notNullValue())));
    }

    private void assertLoggedIn(final Actor actor) {
        then(actor).should(seeThat(
                TheWebPage.title(),
                allOf(
                        containsString("Salesforce"),
                        not(containsString("Login")))));
    }
}
