package com.proedoc;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class Toast {
  public OpportunityPage navigateToCreatedObject(){
    $x("//span[contains(@class, 'toastMessage')]//a").shouldBe(visible).click();
    return new OpportunityPage();
  }
}
