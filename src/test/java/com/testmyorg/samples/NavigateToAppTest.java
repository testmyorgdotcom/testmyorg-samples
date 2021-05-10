package com.testmyorg.samples;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.testmy.screenplay.factory.Login;
import org.testmy.screenplay.factory.ability.NavigateTo;
import org.testmy.screenplay.question.ui.AppName;

public class NavigateToAppTest extends DemoTest {
    @Test
    public void navigateToSalesApp() {
        final String appName = "Sales";
        givenThat(demoActor).wasAbleTo(Login.withSessionIdInFrontDoorUrl());

        when(demoActor).attemptsTo(NavigateTo.appCalled(appName));

        then(demoActor).should(seeThat(AppName.displayed(), is(appName)));
    }
}
