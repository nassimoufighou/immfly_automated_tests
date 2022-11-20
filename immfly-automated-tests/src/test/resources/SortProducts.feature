Feature: Sort products

  Scenario: Products are sorted by price
    Given a user is in the products page
    When the user selects "Product Name" option from the criteria dropdown
    Then the products are sorted by position in ascendant order

  Scenario: Products are sorted by name in descendant order
    Given a user is in the products page
    When the user selects "Product Name" option from the criteria dropdown
     And changes the order to descendant
    Then the products are sorted by product name in descendant order