package org.testmy.screenplay.factory;

import org.testmy.screenplay.act.AddToken;
import org.testmy.screenplay.act.Ensure;
import org.testmy.screenplay.act.task.LoginViaUI;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Performable;

public class Login {
    public static LoginViaUI viaUI() {
        return Instrumented.instanceOf(LoginViaUI.class).newInstance();
    }

    public static Performable usingCookies() {
        return AddToken.asCookieAndOpenUrl();
    }

    public static Performable viaFrontDoorUrl() {
        return AddToken.asSidAndOpenUrl();
    }

    public static Performable viaAPI() {
        return Ensure.partnerConnection();
    }
}
