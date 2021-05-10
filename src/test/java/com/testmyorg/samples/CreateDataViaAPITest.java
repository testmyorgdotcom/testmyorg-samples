package com.testmyorg.samples;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.testmy.data.matchers.Matchers.account;
import static org.testmy.data.matchers.Matchers.hasName;
import static org.testmy.data.matchers.Matchers.ofShape;

import java.util.UUID;

import org.junit.Test;
import org.testmy.data.matchers.HasFields;
import org.testmy.screenplay.act.CreateData;
import org.testmy.screenplay.factory.Login;
import org.testmy.screenplay.factory.ability.Authenticate;
import org.testmy.screenplay.factory.ability.Call;
import org.testmy.screenplay.question.QueryData;

public class CreateDataViaAPITest extends DemoTest {
    @Test
    public void createData() {
        final String accountName = "Test Client:#" + UUID.randomUUID().toString();

        givenThat(demoActor)
                .can(Authenticate.withCredentials())
                .can(Call.partnerApi())
                .wasAbleTo(Login.viaAPI());

        final HasFields accountShape = ofShape(
                account(),
                hasName(accountName));
        when(demoActor).attemptsTo(CreateData.record(accountShape));

        then(demoActor).should(seeThat(QueryData.similarTo(accountShape), is(notNullValue())));
    }
}
