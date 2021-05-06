package org.testmy.screenplay.act;

import com.sforce.ws.ConnectorConfig;

import org.openqa.selenium.Cookie;
import org.testmy.URLParser;
import org.testmy.screenplay.ability.CallPartnerSoapApi;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class AddToken implements Performable {
    private Boolean frontDoor = false;

    public static Performable asCookieAndOpenUrl() {
        return Instrumented.instanceOf(AddToken.class).newInstance();
    }

    public static Performable asSidAndOpenUrl() {
        return Instrumented.instanceOf(AddToken.class).withProperties(true);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        final ConnectorConfig config = CallPartnerSoapApi.as(actor).ensureConnection().getConfig();
        final String sessionId = config.getSessionId();
        final String mainUrl = URLParser.extractMainUrl(config.getServiceEndpoint());
        final String domain = URLParser.extractDomain(mainUrl);

        if (!frontDoor) {
            BrowseTheWeb.as(actor).getDriver().get(mainUrl);
            final Cookie sessionCookie = new Cookie.Builder("sid", sessionId)
                    .domain(domain)
                    .build();
            BrowseTheWeb.as(actor).getDriver().manage().addCookie(sessionCookie);
            BrowseTheWeb.as(actor).getDriver().get(mainUrl);
        }
        else {
            BrowseTheWeb.as(actor).getDriver().get(String.format("%s/secur/frontdoor.jsp?sid=%s", mainUrl, sessionId));
        }
    }
}
