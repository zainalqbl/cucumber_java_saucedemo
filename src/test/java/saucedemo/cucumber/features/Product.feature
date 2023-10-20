@all @product
Feature: Product Purchase on www.saucedemo.com

  @product-cart
  Scenario Outline: Ensure Product to Cart Functionality
    Given I open saucedemo website
    When I login success
    When I add "<product>" to the cart
    And I click the shopping cart button
    Then I verify "<product>" in cart page
    Examples:
    |product                            |
    |Sauce Labs Backpack                |
    |Test.allTheThings() T-Shirt (Red)  |

  @product-checkout
  Scenario Outline: Ensure Checkout Product Functionality
    Given I open saucedemo website
    When I login success
    When I add "<product>" to the cart
    And I click the shopping cart button
    And I click the checkout button
    And I enter "<first_name>" "<last_name>" "<zip>" address
    And I click the continue button
    Then I verify "<status>" checkout result
    Examples:
      |product                            |first_name|last_name|zip   |status       |
      |Sauce Labs Backpack                |test      |last     |12345 |success      |
      |Test.allTheThings() T-Shirt (Red)  |          |         |      |failed       |

  @Product-purchase
  Scenario Outline: Ensure Purchase Product Functionality
    Given I open saucedemo website
    When I login success
    When I add "<product>" to the cart
    And I click the shopping cart button
    And I click the checkout button
    And I enter "<first_name>" "<last_name>" "<zip>" address
    And I click the continue button
    And I click the finish button
    Then I verify purchase result
    Examples:
      | product | first_name | last_name | zip |
      |Sauce Labs Backpack                |test      |last     |12345 |
      |Test.allTheThings() T-Shirt (Red)  |tes       |tes      |12345 |

  @Product-no-access
  Scenario: Access url inventory without login
    Given I open saucedemo inventory website
    Then I verify result