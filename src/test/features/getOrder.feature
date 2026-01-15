Feature: Validate Create Order feature
  I want to get a PayPal order using this feature

  Scenario Outline:Validate Get Order feature with valid details
    Given get the access token from paypal API
    When user calls get order API using below Order ID
      | OrderID | <OrderID> |
    Then user verify status code as "<Status Code>"

    Examples:
      | OrderID           | Status Code |
      | 7C683241HP666834R | 200         |
