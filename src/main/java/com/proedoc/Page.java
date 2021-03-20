package com.proedoc;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Selenide.$$x;

public class Page {
  String pageName;
  public Page(final String pageName){
    this.pageName = pageName;
  }
  public Boolean isLoaded(){
    return $$x("//div[contains(@class, 'loadingOverlay')]").shouldBe(empty).isEmpty();
  }
}
