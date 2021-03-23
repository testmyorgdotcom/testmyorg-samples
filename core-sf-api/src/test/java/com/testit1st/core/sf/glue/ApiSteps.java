package com.testit1st.core.sf.glue;

import static com.testit1st.core.sf.matchers.Matchers.client;
import static com.testit1st.core.sf.matchers.Matchers.hasId;
import static com.testit1st.core.sf.matchers.Matchers.hasName;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import com.sforce.soap.partner.sobject.SObject;
import com.testit1st.core.sf.matchers.DataManager;

import org.hamcrest.Matcher;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ApiSteps {
  @Autowired
  DataManager dataManager;
  
  @Given("{string} is our {string}")
  public void is_our_client(final String clientName, final String objectType) {
    final Matcher<SObject> sObjectShape = allOf(
      is(client()),
      hasName(clientName)
    );
    final SObject createdClient = dataManager.ensureSObject(sObjectShape);

    assertThat(createdClient, hasId());
  }
  @Then("I can find it in Salesforce Org")
  public void i_can_find_it_in_my_salesforce_org() {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
  }  
}
