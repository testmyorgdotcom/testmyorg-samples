package org.testmy.screenplay.ability;

import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

import lombok.Getter;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class CallPartnerSoapApi implements Ability {
    private String connectionUrl;
    @Getter
    private ConnectorConfig config;
    @Getter
    private PartnerConnection connection;

    public CallPartnerSoapApi(final String apiVersion) {
        this.connectionUrl = "https://login.salesforce.com/services/Soap/u/" + apiVersion;
    }

    public static CallPartnerSoapApi ofVersion(final String apiVersion) {
        return new CallPartnerSoapApi(apiVersion);
    }

    public void withConfig(ConnectorConfig config) {
        config.setAuthEndpoint(this.connectionUrl);
        this.config = config;
    }

    public void connect() {

        try {
            this.connection = new PartnerConnection(getConfig());
        }
        catch (final ConnectionException e) {
            e.printStackTrace();
            // TODO: throw exception so test fails
        }
    }

    public static CallPartnerSoapApi as(final Actor actor) {
        final CallPartnerSoapApi ability = actor.abilityTo(CallPartnerSoapApi.class);

        if (null == ability) {
            // TODO: add standard pattern for no ability for Actor
            throw new IllegalArgumentException("Actor does not have ability: ");
        }
        return ability;
    }
}
