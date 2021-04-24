package org.testmy.screenplay.task;

import com.sforce.ws.ConnectorConfig;

import org.testmy.screenplay.ability.CallPartnerSoapApi;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;

@AllArgsConstructor
public class CreatePartnerSoapConfig implements Performable{
    private final String username;
    private final String password;

    public static Performable with(final String username, final String password) {
        return new CreatePartnerSoapConfig(username, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        final ConnectorConfig config = new ConnectorConfig();
        config.setUsername(username);
        config.setPassword(password);
        
        actor.abilityTo(CallPartnerSoapApi.class).withConfig(config);
    }
}
