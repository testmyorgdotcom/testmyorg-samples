package org.testmy.screenplay.ability;

import java.util.Optional;
import java.util.function.Function;

import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectorConfig;

import lombok.Getter;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.RefersToActor;

public class CallPartnerSoapApi implements Ability, RefersToActor {
    @Getter
    private Actor actor;
    // TODO: make configurable
    private String connectionUrl = "https://login.salesforce.com/services/Soap/u/51";
    private Function<ConnectorConfig, PartnerConnection> connectionFactory;
    @Getter
    private Optional<PartnerConnection> connection = Optional.empty();

    public CallPartnerSoapApi(final Function<ConnectorConfig, PartnerConnection> connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public PartnerConnection ensureConnection() {

        if (!connection.isPresent()) {
            final AuthenticateWithCredentials credentials = AuthenticateWithCredentials.as(actor);
            final ConnectorConfig config = new ConnectorConfig();
            config.setUsername(credentials.getUsername());
            config.setPassword(credentials.getPassword());
            config.setAuthEndpoint(connectionUrl);
            this.connection = Optional.of(connectionFactory.apply(config));
        }
        return connection.get();
    }

    public static CallPartnerSoapApi as(final Actor actor) {
        return AbilityAs.actor(actor, CallPartnerSoapApi.class);
    }

    @Override
    public <T extends Ability> T asActor(Actor actor) {
        this.actor = actor;
        return (T) this;
    }
}
