@AllProduct
Feature: List of Product

  Scenario: Get list of products
    Given I perform get operation "/products" to get the list of product
    Then the product in 1 as the price as "9.25"


