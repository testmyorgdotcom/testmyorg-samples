package com.testmyorg.samples.data;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.testmy.data.matchers.Matchers.hasField;
import static org.testmy.data.matchers.Matchers.hasId;
import static org.testmy.data.matchers.Matchers.ofShape;
import static org.testmy.data.matchers.ObjectMatchers.contact;

import com.sforce.soap.partner.sobject.SObject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testmy.config.Config;
import org.testmy.data.TestDataManager;
import org.testmy.data.matchers.HasFields;
import org.testmy.screenplay.act.CleanData;
import org.testmy.screenplay.act.task.StoreObjectAtScene;
import org.testmy.screenplay.factory.KeyboardShortcuts;
import org.testmy.screenplay.factory.Login;
import org.testmy.screenplay.factory.ability.Authenticate;
import org.testmy.screenplay.factory.ability.Call;
import org.testmy.screenplay.question.QueryData;
import org.testmy.screenplay.ui.GlobalActions;
import org.testmy.screenplay.ui.Toast;
import org.testmy.screenplay.ui.global.composer.NewContact;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.waits.WaitUntil;
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

        // when(admin).attemptsTo(CreateRecord.viaGlobalAction(Config.GLOBAL_ACTION_NEW_CONTACT).of(contactShape));
        final SObject sObject = new TestDataManager().constructSObject(contactShape);
        when(admin).attemptsTo(
                Click.on(GlobalActions.createButton()),
                WaitUntil.the(GlobalActions.createMenuList(), isVisible()),
                Click.on(GlobalActions.createMenuListItem(Config.GLOBAL_ACTION_NEW_CONTACT)),
                WaitUntil.the(GlobalActions.form(Config.GLOBAL_ACTION_NEW_CONTACT), isVisible()),

                // PopulateComposer.from(contactShape),
                Check.whether(sObject.getField("LastName") != null)
                        .andIfSo(SendKeys.of(sObject.getField("LastName").toString()).into(NewContact.lastName())),
                Check.whether(sObject.getField("FirstName") != null)
                        .andIfSo(SendKeys.of(sObject.getField("FirstName").toString()).into(NewContact.firstName())),
                Check.whether(sObject.getField("Email") != null)
                        .andIfSo(SendKeys.of(sObject.getField("Email").toString()).into(NewContact.email())),
                Check.whether(sObject.getField("Phone") != null)
                        .andIfSo(SendKeys.of(sObject.getField("Phone").toString()).into(NewContact.phone())),
                KeyboardShortcuts.save(),
                WaitUntil.the(Toast.success(), isVisible()),
                Click.on(Toast.objectName()),
                StoreObjectAtScene.intoDataCache());

        then(admin).should(seeThat(QueryData.similarTo(contactShape), hasId()));
    }
}
