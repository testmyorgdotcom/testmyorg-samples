package com.apolya.poc;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import org.junit.Test;
import org.openqa.selenium.By;

public class SampleTest {
  @Test
  public void userCanLoginByUsername() {
    open("https://google.com/ncr");
    $(By.name("q")).val("selenide").pressEnter();
    $$("#res .g").shouldHave(sizeGreaterThan(1));
    $("#res .g").shouldBe(visible).shouldHave(
        text("Selenide: concise UI tests in Java"),
        text("selenide.org"));
  }
}
