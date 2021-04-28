package org.testmy.screenplay.task;

import java.net.MalformedURLException;
import java.net.URL;

import com.sforce.ws.ConnectorConfig;

import org.openqa.selenium.Cookie;
import org.testmy.screenplay.ability.CallPartnerSoapApi;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class AddToken implements Performable {

    public static Performable asCookieAndOpenUrl() {
        return Instrumented.instanceOf(AddToken.class).newInstance();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        final ConnectorConfig config = actor.abilityTo(CallPartnerSoapApi.class).getConfig();
        final String urlEndpoint = config.getServiceEndpoint();

        try {
            final URL url = new URL(urlEndpoint.substring(0, urlEndpoint.indexOf("/services/")));
            BrowseTheWeb.as(actor).getDriver().get(url.toString());
            final Cookie sessionCookie = new Cookie.Builder("sid", config.getSessionId())
                    .domain(url.getHost())
                    .build();
            BrowseTheWeb.as(actor).getDriver().manage().addCookie(sessionCookie);
            BrowseTheWeb.as(actor).getDriver().get(url.toString());
        }
        catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
