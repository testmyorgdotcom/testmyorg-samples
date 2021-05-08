Feature: Login into Salesforce

    Background: Admin User
        Given John is an Admin

    # TODO: add proxy dimension to every scenario
    Scenario: Via login URL
        Then John is able to authenticate via Salesforce login URL

    Scenario: Using Session Id obtained via api call
        Given John was able to authenticate via Salesforce api
        Then John can login into Salesforce without entering credentials

    @Pending
    Scenario: Admin can login as someone else
        Given Chloe is a Sales Manager
        And John is logged into Salesforce
        Then John is able to "Login As" Chloe

    @Pending
    Scenario: Using Certificate to obtain Session Id