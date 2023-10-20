@all @logout
Feature: Logout from www.saucedemo.com
  Scenario: Ensure Logout Functionality
    Given I open saucedemo website 1
    When I login success 1
    And I click menu button
    And I click logout button
    Then I verify logout result
