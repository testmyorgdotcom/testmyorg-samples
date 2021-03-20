package com.proedoc;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Selenide;

public class LoginPage {
  public LoginPage open() {
    Selenide.open("https://login.salesforce.com");
    return this;
  }

  public AppLauncher loginWith(String username, String password) {
    $("#username").val(username);
    $("#password").val(password).pressEnter();
    return new AppLauncher();
  }
}
