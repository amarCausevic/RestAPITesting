Feature: User will delete newly created booking, with correct token that was obtained from Login API

  Scenario: As user I want to login, create new booking and delete it
    Given user sends a DELETE request to the endpoint
    When endpoint is reached, booking was deleted
    Then validate booking does not exists anymore in database
    And validate DELETE status code 200