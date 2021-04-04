package org.testmy.screenplay.task;

import org.testmy.screenplay.ability.CallPartnerSoapApi;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;

public class Establish implements Performable{

    public Establish(){};
    public static Establish partnerConnection() {
        return new Establish();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.abilityTo(CallPartnerSoapApi.class).connect();
    }
}
