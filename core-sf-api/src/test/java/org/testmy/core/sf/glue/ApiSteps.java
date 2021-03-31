package org.testmy.core.sf.glue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testmy.core.sf.matchers.Matchers.client;
import static org.testmy.core.sf.matchers.Matchers.hasId;
import static org.testmy.core.sf.matchers.Matchers.hasName;
import static org.testmy.core.sf.matchers.Matchers.ofShape;

import com.sforce.soap.partner.sobject.SObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testmy.core.sf.TestDataManager;
import org.testmy.core.sf.config.CucumberSpringConfiguration;
import org.testmy.core.sf.matchers.ConstructingMatcher;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = CucumberSpringConfiguration.class)
public class ApiSteps {
  @Autowired
  TestDataManager dataManager;
  
  @Given("^(.+) is our (.+)$")
  public void is_our_client(final String clientName, final String objectType) {
    final ConstructingMatcher sObjectShape = ofShape(
      client(),
      hasName(clientName)
    );
    final SObject createdClient = dataManager.getOrCreate(sObjectShape);

    assertThat(createdClient, hasId());
  }
  @Then("I can find it in my Salesforce Org")
  public void i_can_find_it_in_my_salesforce_org() {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
  }  
}
