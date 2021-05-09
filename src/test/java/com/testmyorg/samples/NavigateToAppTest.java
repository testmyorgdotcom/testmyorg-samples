package com.testmyorg.samples;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testmy.screenplay.fact.PersonaBehaviour;
import org.testmy.screenplay.factory.Login;
import org.testmy.screenplay.factory.ability.Authenticate;
import org.testmy.screenplay.factory.ability.Call;
import org.testmy.screenplay.factory.ability.NavigateTo;
import org.testmy.screenplay.question.ui.AppName;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)
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
                .can(Call.partnerApi())
                .can(BrowseTheWeb.with(browser))
                .wasAbleTo(Login.withSessionIdInFrontDoorUrl());
    }

    @Test
    public void navigateToSalesApp() {
        final String appName = "Sales";
        when(salesManager).attemptsTo(NavigateTo.appCalled(appName));

        then(salesManager).should(seeThat(AppName.displayed(), is(appName)));
    }
}
