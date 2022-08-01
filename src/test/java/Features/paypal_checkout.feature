@paypal_checkout
Feature:  Paypal Checkout
@Ignored
Scenario: Test paypal checkout

Given user is already on Website Home Page pp
Then user navigates to sign up page pp
Then user create a new ac count pp
Then user is redirected to pricing page and choose a plan to pay pp
Then user is redirected to checkout page pp
Then user proceed to pay with paypal pp
Then paypal popup appears and user navigates back to my account pp
Then user deleted the account pp
