package com.testmyorg.samples;

import static net.serenitybdd.screenplay.GivenWhenThen.and;
import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testmy.screenplay.act.CleanData;
import org.testmy.screenplay.fact.PersonaBehaviour;
import org.testmy.screenplay.factory.Login;
import org.testmy.screenplay.factory.ability.Authenticate;
import org.testmy.screenplay.factory.ability.Call;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)
public abstract class DemoTest {
    @Managed
    WebDriver browser;
    Actor demoActor;
    Actor admin;

    @Before
    public void before() {
        admin = Actor.named("Admin");
        givenThat(admin).has(PersonaBehaviour.of("Admin"));
        and(admin)
                .can(Authenticate.withCredentials())
                .can(Call.partnerApi())
                .wasAbleTo(Login.viaAPI());;

        demoActor = Actor.named("Jane");
        givenThat(demoActor).has(PersonaBehaviour.of("Sales Manager"));
        and(demoActor)
                .can(Authenticate.withCredentials())
                .can(Call.partnerApi())
                .can(BrowseTheWeb.with(browser));
    }

    @After
    public void after() {
        admin.attemptsTo(CleanData.afterTest());
    }
}
