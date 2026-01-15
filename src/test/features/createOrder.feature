Feature: Validate Create Order feature
  I want to create a PayPal order using this feature

  Scenario Outline:Validate Create Order feature with valid details
    Given get the access token from paypal API
    When create order payload with
      | intent                  | <intent>                  |
      | paymentMethodPreference | <paymentMethodPreference> |
      | landingPage             | <landingPage>             |
      | shippingPreference      | <shippingPreference>      |
      | userAction              | <userAction>              |
      | returnUrl               | <returnUrl>               |
      | cancelUrl               | <cancelUrl>               |
      | invoiceId               | <invoiceId>               |
      | currency                | <currency>                |
      | itemTotal               | <itemTotal>               |
      | shippingAmount          | <shippingAmount>          |
      | totalAmount             | <totalAmount>             |

    And user calls create order API
    Then order should be created with status code 200
    Examples:
      | intent  | paymentMethodPreference    | landingPage | shippingPreference | userAction | returnUrl                     | cancelUrl                     | invoiceId | currency | itemTotal | shippingAmount | totalAmount |
      | CAPTURE | IMMEDIATE_PAYMENT_REQUIRED | LOGIN       | GET_FROM_FILE      | PAY_NOW    | https://example.com/returnUrl | https://example.com/cancelUrl | 90210     | USD      | 220.00    | 10.00          | 230.00      |

