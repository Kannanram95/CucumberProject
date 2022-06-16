Feature: To validate the login functionality of facebook

  Background: 
    Given User should hit the facebook url in GC browser

  Scenario: To validate using valid username and password
    When user should enter the username and password
    And user should click the login button
    And check whether user is directed to user page
    Then user should quit browser
