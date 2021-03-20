package com.proedoc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.sforce.soap.partner.sobject.SObject;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class SalesforceLoginIT {
  final String username = System.getProperty("sf.username");
  final String password = System.getProperty("sf.password");
  @Test
  public void canLoginToSalesforce(){
    final LoginPage loginPage = new LoginPage();
    final App salesApp = loginPage.open().loginWith(username, password).openApp("Sales");

    assertTrue("could not log in into salesforce org", salesApp.isDisplayed());
  }
  @Test
  public void canCreateOpportunityViaUI(){
    final LoginPage loginPage = new LoginPage();
    final AppLauncher appLauncher = loginPage.open().loginWith(username, password);
    final App salesApp = appLauncher.openApp("Sales");
    assertTrue("could not log in into salesforce org", salesApp.isDisplayed());

    final GlobalActions globalActions = salesApp.globalActions();
    final NewOpportunityPage page = globalActions.globalAction("New Opportunity");
    final Toast toast = page.populateDetails("Test Opportunity", "Prospecting").create();
    final String sfId = toast.navigateToCreatedObject().getId();
    assertTrue("page is not loaded after selection of the page tab", StringUtils.isNotBlank(sfId));

    final SfApi sfApi = new SfApi();
    final SObject sfObject = sfApi.queryObject(sfId);

    assertNotNull(sfObject);
    assertEquals(sfId, sfObject.getId());
  }
}
