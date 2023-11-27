Feature: Validating CORRECT endpoint reposes for booking project

  Scenario: As user I want to login to the https://restful-booker.herokuapp.com/ with VALID credentials
    Given username "admin" and password "password123" user sends a request to the endpoint
    When endpoint is reached, response is send back to the user
    Then validate response body has returned a sign in token
    And validate status code 200

  Scenario: As user I want to login to the https://restful-booker.herokuapp.com/ with INVALID credentials
    Given username "admins" and password "password1234" user sends a request to the endpoint
    When endpoint is reached, response is send back to the user
    Then validate response body has message "Bad credentials"
    And validate status code 200