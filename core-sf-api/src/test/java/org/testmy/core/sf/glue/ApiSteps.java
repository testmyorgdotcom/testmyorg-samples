package org.testmy.core.sf.glue;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.testmy.core.sf.matchers.Matchers.client;
import static org.testmy.core.sf.matchers.Matchers.hasId;
import static org.testmy.core.sf.matchers.Matchers.hasName;

import com.sforce.soap.partner.sobject.SObject;

import org.hamcrest.Matcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.testmy.core.sf.matchers.TestDataManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ApiSteps {
  @Autowired
  TestDataManager dataManager;
  
  @Given("(.+) is our (.+)")
  public void is_our_client(final String clientName, final String objectType) {
    final Matcher<SObject> sObjectShape = allOf(
      is(client()),
      hasName(clientName)
    );
    // final SObject createdClient = dataManager.ensureSObject(sObjectShape);

    // assertThat(createdClient, hasId());
  }
  @Then("I can find it in my Salesforce Org")
  public void i_can_find_it_in_my_salesforce_org() {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
  }  
}
