package it.ee.test.glue;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.openqa.selenium.By;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GlueCode {
  @Then("true can be parsed")
  public void true_can_be_parsed() {
      assertEquals(true, Boolean.TRUE);
  }
  @Then("false can be parsed")
  public void false_can_be_parsed() {
      assertFalse(Boolean.FALSE);
  }
  @Given("an open browser with google.com")
  public void openGoogleSearch() {
    open("https://google.com/ncr");
  }

  @When("a keyword {string} is entered in input field")
  public void enterKeyword(String keyword) {
    $(By.name("q")).val(keyword).pressEnter();
  }

  @Then("at least top {int} matches should be shown")
  public void topTenMatchesShouldBeShown(int resultsCount) {
    $$("#res .g").shouldHave(sizeGreaterThanOrEqual(resultsCount));
  }

  @Then("the first one should contain {string}")
  public void theFirstOneShouldContainKeyword(String expectedText) {
    $("#res .g").shouldHave(text(expectedText));
  }  
}
