package com.proedoc;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;

public class NavigationBar {
  public Page navigateToPage(final String pageName){
    final SelenideElement navigationBar = $x("//one-app-nav-bar").shouldBe(visible);
    SelenideElement visibleNavigationItem = navigationBar.$x("//one-app-nav-bar-item-root[@aria-hidden='false' and .//a[@title='" + pageName + "']]");
    if(!visibleNavigationItem.exists()){
      final SelenideElement more = navigationBar.$x("//one-app-nav-bar-item-root[@aria-hidden='false' and //a[@title='More']]");
      visibleNavigationItem = more.$$(By.xpath("//span")).filter(exactText(pageName)).first();
    }
    visibleNavigationItem.click();
    if("Opportunities".equals(pageName)){
      return new OpportunitiesPage();
    }
    return new Page(pageName);
  }
  
  public Boolean isActiveItem(String pageName) {
    final SelenideElement navigationBar = $x("//one-app-nav-bar").shouldBe(visible);
    return navigationBar.$x("//one-app-nav-bar-item-root[contains(@class, 'slds-is-active') and .//a[@title='" + pageName + "']]").shouldBe(visible).exists();
  }
}
