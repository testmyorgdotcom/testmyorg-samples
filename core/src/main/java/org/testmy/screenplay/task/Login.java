package org.testmy.screenplay.task;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Performable;

public class Login {
    public static LoginViaUI viaUI() {
        return Instrumented.instanceOf(LoginViaUI.class).newInstance();
    }

    public static Performable viaSoapApi() {
        return Instrumented.instanceOf(LoginViaAPI.class).newInstance();
    }
}
