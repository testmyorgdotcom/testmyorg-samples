package com.proedoc;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

public class App {
	private String appName;
	private NavigationBar navigationBar;
	public App(final String appName){
		this.appName = appName;
		this.navigationBar = new NavigationBar();
	}
	public boolean isDisplayed() {
		return appName.equals($(By.className("oneAppNavContainer")).findElement(By.className("appName")).getText());
	}
	public Page openPageTab(final String pageTabName){
		return navigationBar.navigateToPage(pageTabName);
	}
	public GlobalActions globalActions(){
		return new GlobalActions();
	}
}
