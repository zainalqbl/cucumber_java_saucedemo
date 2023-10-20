@all @login
Feature: Login to www.saucedemo.com

  Scenario Outline: Ensure Login Functionality
    Given I open the saucedemo website
    When I enter "<username>" as username
    And I enter "<password>" as password
    And I click the login button
    Then I verify "<status>" login result

    Examples:
      |username           |password       |status|
      |standard_user      |secret_sauce   |success|
      |locked_out_user    |secret_sauce  |failed|
