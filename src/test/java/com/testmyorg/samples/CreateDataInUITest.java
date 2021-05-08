package com.testmyorg.samples;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static org.testmy.data.matchers.Matchers.account;
import static org.testmy.data.matchers.Matchers.contact;
import static org.testmy.data.matchers.Matchers.hasField;
import static org.testmy.data.matchers.Matchers.hasId;
import static org.testmy.data.matchers.Matchers.hasName;
import static org.testmy.data.matchers.Matchers.ofShape;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testmy.screenplay.act.CleanData;
import org.testmy.screenplay.act.CreateData;
import org.testmy.screenplay.act.task.CreateContact;
import org.testmy.screenplay.act.task.SearchForAccount;
import org.testmy.screenplay.fact.PersonaBehaviour;
import org.testmy.screenplay.factory.Login;
import org.testmy.screenplay.factory.ability.Authenticate;
import org.testmy.screenplay.factory.ability.Call;
import org.testmy.screenplay.question.QueryData;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)
public class CreateDataInUITest {
    @Managed
    WebDriver browser;

    private Actor admin = Actor.named("Adnim");

    @Before
    public void before() {
        admin.has(PersonaBehaviour.of("Admin"));
        givenThat(admin)
                .can(Authenticate.withCredentials())
                .can(Call.partnerApi())
                .can(BrowseTheWeb.with(browser));
        admin.wasAbleTo(Login.withSessionIdInFrontDoorUrl());
    }

    @After
    public void after() {
        admin.attemptsTo(CleanData.afterTest());
    }

    @Test
    public void addNewContactToExistingAccount() {
        final String accountName = "Serenity Test";
        givenThat(admin).wasAbleTo(CreateData.record(ofShape(
                account(),
                hasName(accountName))));

        admin.attemptsTo(
                SearchForAccount.witnName(accountName),
                CreateContact.withLastName("Panda"));

        then(admin).should(seeThat(
                QueryData.similarTo(
                        ofShape(
                                contact(),
                                hasField("Account.Name", accountName))),
                hasId()));
    }
}
