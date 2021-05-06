package com.testmyorg.samples.navigation;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;

import org.testmy.screenplay.ui.AppLauncher;
import org.testmy.screenplay.ui.WebPage;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateToApp implements Interaction{
    private String appName;

    public static Interaction called(final String appName) {
        return Instrumented.instanceOf(NavigateToApp.class).withProperties(appName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        final String currentUrl = actor.abilityTo(BrowseTheWeb.class).getDriver().getCurrentUrl();
        //TODO construnct URL
        // [ ] Remove code dupdlate about currentUrl resolution and replace it with action
        // actor.attemptsTo(
        //     Open.url()
        //     WaitUntil.the(WebPage.loadingLogo(), isNotVisible()),
        //     Click.on(AppLauncher.icon()),
        //     Send
        // );
        // TODO Auto-generated method stub
        
    }
}
