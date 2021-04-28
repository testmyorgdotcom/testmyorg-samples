package org.testmy.screenplay.task;

import org.testmy.screenplay.ability.AuthenticateWithCredentials;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;

public class LoginViaAPI implements Performable {

    @Override
    public <T extends Actor> void performAs(final T actor) {
        final AuthenticateWithCredentials authenticated = AuthenticateWithCredentials.as(actor);
        actor.attemptsTo(
                CreatePartnerSoapConfig.with(authenticated.getUsername(), authenticated.getPassword()),
                Establish.partnerConnection());
    }
}
