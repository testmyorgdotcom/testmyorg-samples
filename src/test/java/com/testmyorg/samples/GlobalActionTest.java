package com.testmyorg.samples;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.testmy.data.matchers.Matchers.contact;
import static org.testmy.data.matchers.Matchers.hasField;
import static org.testmy.data.matchers.Matchers.hasId;
import static org.testmy.data.matchers.Matchers.ofShape;

import org.junit.Test;
import org.testmy.data.matchers.HasFields;
import org.testmy.screenplay.act.task.CreateRecord;
import org.testmy.screenplay.factory.Login;
import org.testmy.screenplay.question.QueryData;

public class GlobalActionTest extends DemoTest {
    @Test
    public void createContactViaGlobalActions() {
        final HasFields ofShape = ofShape(
                contact(),
                hasField("LastName", "Test User Last Name"));

        givenThat(demoActor).wasAbleTo(Login.withSessionIdInFrontDoorUrl());

        when(demoActor).attemptsTo(CreateRecord.viaGlobalAction(ofShape));

        then(demoActor).should(seeThat(QueryData.similarTo(ofShape), hasId()));
    }
}
