package com.testmyorg.samples.navigation;

import static net.serenitybdd.screenplay.GivenWhenThen.when;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.testmy.screenplay.fact.PersonaBehaviour;
import org.testmy.screenplay.factory.Login;
import org.testmy.screenplay.factory.ability.Authenticate;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;

public class NavigateToAppTest {
    @Managed
    WebDriver browser;
    Actor salesManager;

    @Before
    public void before() {
        salesManager = Actor.named("Jane");
        salesManager.has(PersonaBehaviour.of("Sales Manager"));
        salesManager
                .can(Authenticate.withCredentials())
                .can(BrowseTheWeb.with(browser))
                .wasAbleTo(Login.withSessionIdAsCookies());
    }

    @Test
    public void navigateToSalesApp() {
        when(salesManager).attemptsTo(NavigateToApp.called("Sales"));
    }
}
