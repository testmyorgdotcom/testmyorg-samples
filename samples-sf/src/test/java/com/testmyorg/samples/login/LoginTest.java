package com.testmyorg.samples.login;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testmy.screenplay.ability.Authenticate;
import org.testmy.screenplay.ability.CallPartnerSoapApi;
import org.testmy.screenplay.fact.PersonaBehaviour;
import org.testmy.screenplay.question.PartnerConnection;
import org.testmy.screenplay.task.AddToken;
import org.testmy.screenplay.task.Login;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)
public class LoginTest {
    @Managed
    WebDriver browser;

    @Test
    public void authenticateViaLoginForm(){
        final Actor mike = Actor.named("Mike");
        mike.has(PersonaBehaviour.of("Admin"));

        givenThat(mike)
            .can(Authenticate.withCredentials())
            .can(BrowseTheWeb.with(browser));

        when(mike).attemptsTo(Login.viaUI());

        then(mike).should(seeThat(TheWebPage.title(), allOf(containsString("Home"), containsString("Salesforce"))));
    }
    @Test
    public void authenticateViaAPI(){
        final Actor mike = Actor.named("Mike");
        mike.has(PersonaBehaviour.of("Admin"));

        givenThat(mike)
            .can(Authenticate.withCredentials())
            .can(CallPartnerSoapApi.ofVersion("51"));

        when(mike).attemptsTo(Login.viaSoapApi());

        then(mike).should(seeThat(PartnerConnection.sessionId(), is(notNullValue())));
    }

    @Test
    public void useApiTokenInWebFlow(){
        final Actor mike = Actor.named("Mike");
        mike.has(PersonaBehaviour.of("Admin"));

        givenThat(mike)
            .can(Authenticate.withCredentials())
            .can(CallPartnerSoapApi.ofVersion("51"))
            .can(BrowseTheWeb.with(browser))
            .wasAbleTo(Login.viaSoapApi());
        
        when(mike).attemptsTo(
            AddToken.asCookieAndOpenUrl()
        );

        then(mike).should(seeThat(TheWebPage.title(), allOf(containsString("Home"), containsString("Salesforce"))));
    }
    // TODO: support login as - too difficult, looks like need to hardcode url or do via UI actions Low Priority for now
}
