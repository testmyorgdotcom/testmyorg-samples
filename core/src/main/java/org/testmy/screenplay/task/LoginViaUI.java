package org.testmy.screenplay.task;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;

import org.testmy.screenplay.ability.AuthenticateWithCredentials;
import org.testmy.screenplay.ui.LoginForm;
import org.testmy.screenplay.ui.LoginPage;
import org.testmy.screenplay.ui.WebPage;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

public class LoginViaUI implements Task {
    private LoginPage loginPage;

    public LoginViaUI() {
    }

    public static Login viaUI() {
        return Instrumented.instanceOf(Login.class).newInstance();
    }

    @Override
    @Step("{0} logs into Salesforce")
    public <T extends Actor> void performAs(T actor) {
        final AuthenticateWithCredentials authenticated = AuthenticateWithCredentials.as(actor);
        actor.attemptsTo(
            Open.browserOn(loginPage),
            SendKeys.of(authenticated.getUsername()).into(LoginForm.usernameInput()),
            SendKeys.of(authenticated.getPassword()).into(LoginForm.passwordInput()),
            Click.on(LoginForm.loginButton()),
            WaitUntil.the(WebPage.loadingLogo(), isNotVisible())
        );
    }
}
