package com.proedoc;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

import com.sforce.soap.partner.sobject.SObject;

public class NewOpportunityPage {
  public NewOpportunityPage populateDetails(final SObject opportunity) {
    return this;
  }

  public Toast create() {
    $x("//div[contains(@class, 'forceChatterPublisherQuickAction')]//div[contains(@class,'bottomBar')]//button").shouldBe(visible).click();
    $x("//div[contains(@class, 'forceChatterPublisherQuickAction')]").shouldNotBe(visible);
    return new Toast();
  }

  public NewOpportunityPage populateDetails(String opportunityName, String stageName) {
    $x("//input[@id=string(//div[contains(@class, 'forceChatterPublisherQuickAction')]//label[*[text()='" + "Opportunity Name" + "']]/@for)]").shouldBe(visible).setValue(opportunityName);
    
    $x("//div[contains(@class, 'forceChatterPublisherQuickAction')]//div[span[*[text()='Stage']]]//a").shouldBe(visible).click();
    $x("//div[@aria-labelledby=string(//div[contains(@class, 'forceChatterPublisherQuickAction')]//div[span[*[text()='Stage']]]//div[@class='uiPopupTrigger']/@id)]//ul/li[*[text()='" + stageName + "']]").shouldBe(visible).click();
    return this;
  }
}
