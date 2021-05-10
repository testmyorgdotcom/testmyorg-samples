package com.testmyorg.samples;

import static net.serenitybdd.screenplay.GivenWhenThen.and;
import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static org.testmy.data.matchers.Matchers.account;
import static org.testmy.data.matchers.Matchers.contact;
import static org.testmy.data.matchers.Matchers.hasField;
import static org.testmy.data.matchers.Matchers.hasId;
import static org.testmy.data.matchers.Matchers.hasName;
import static org.testmy.data.matchers.Matchers.ofShape;

import org.junit.Test;
import org.testmy.screenplay.act.CreateData;
import org.testmy.screenplay.act.task.CreateContact;
import org.testmy.screenplay.act.task.SearchForAccount;
import org.testmy.screenplay.factory.Login;
import org.testmy.screenplay.question.QueryData;

import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class CreateDataInUITest extends DemoTest {
    @Test
    public void addNewContactToExistingAccount() {
        final String accountName = "Serenity Test";
        givenThat(admin)
                .can(BrowseTheWeb.with(browser))
                .wasAbleTo(CreateData.record(ofShape(
                        account(),
                        hasName(accountName))));
        and(admin).wasAbleTo(Login.withSessionIdInFrontDoorUrl());

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