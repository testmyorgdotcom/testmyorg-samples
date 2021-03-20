package com.proedoc;

public class OpportunitiesPage extends Page {
  public OpportunitiesPage(){
    super("Opportunities");
  }
  public NewOpportunityPage openNewOpportunityPage(){
    return new NewOpportunityPage();
  }
}
