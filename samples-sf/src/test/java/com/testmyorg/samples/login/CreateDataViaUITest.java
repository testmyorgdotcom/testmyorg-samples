package com.testmyorg.samples.login;

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
import org.testmy.screenplay.ability.Authenticate;
import org.testmy.screenplay.ability.CallPartnerSoapApi;
import org.testmy.screenplay.fact.PersonaBehaviour;
import org.testmy.screenplay.question.QueryData;
import org.testmy.screenplay.task.CleanData;
import org.testmy.screenplay.task.CreateContact;
import org.testmy.screenplay.task.CreateData;
import org.testmy.screenplay.task.Login;
import org.testmy.screenplay.task.SearchForAccount;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)
public class CreateDataViaUITest {
    @Managed
    WebDriver browser;

    private Actor admin = Actor.named("Adnim");

    @Before
    public void before() {
        admin.has(PersonaBehaviour.of("Admin"));
        admin
                .can(Authenticate.withCredentials())
                .can(CallPartnerSoapApi.ofVersion("51"))
                .can(BrowseTheWeb.with(browser))
                .wasAbleTo(Login.viaUI());
        admin.wasAbleTo(Login.viaSoapApi());
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
