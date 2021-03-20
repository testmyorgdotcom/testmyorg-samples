package com.proedoc;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class AppLauncher {
  public App openApp(final String appName){
    $x("/html/body//div[div//text()='Loading']").shouldNotBe(visible);
		$x("//button[contains(@class, 'salesforceIdentityAppLauncherHeader')]").shouldBe(visible).click();
		$x("//one-app-launcher-search-bar//input").shouldBe(visible).setValue(appName);
    $$x("//one-app-launcher-menu-item").filter(exactText(appName)).get(0).click();
    return new App(appName);
  }
}
