Feature: Validating CORRECT endpoint reposes for booking project

  Scenario: As user I want to login to the https://restful-booker.herokuapp.com/ with valid credentials
    Given User calls sets initial api call prerequisites
    When User sends correct api call payload
    Then User receives Authorization token
    And User is successfully logged into Booking app

  Scenario: As user I want to login to the https://restful-booker.herokuapp.com/ with invalid credentials
    Given User calls sets initial api call prerequisites
    When User sends invalid api call payload
    Then User does not receive Authorization token
    And User is not logged into Booking app with reason "Bad credentials"