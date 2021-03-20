package com.proedoc;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class GlobalActions {
  public GlobalActions open() {
    // $(".globalCreateContainer").shouldBe(visible).sel().
    // $(".globalCreateMenuList.visible.positioned").shouldBe(visible);
    return this;
  }

  public NewOpportunityPage globalAction(final String actionDisplayName) {
    // open();
    $x("//a[contains(@class, 'globalCreateTrigger')]").shouldBe(visible).click();
    $x("//li[contains(@class, 'oneGlobalCreateItem')]//a[@title='" + actionDisplayName + "']").shouldBe(visible).click();

    return new NewOpportunityPage();
  }
}
