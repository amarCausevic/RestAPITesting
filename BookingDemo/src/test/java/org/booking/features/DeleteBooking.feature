Feature: User will delete newly created booking, with correct token that was obtained from Login API

  Scenario: As user I want to login, create new booking and delete it
    Given user sends a DELETE request to the endpoint
    When endpoint is reached, validate "Created" is returned into the response body
    Then users triggers search for deleted BOOKING ID, in response users gets "Not Found"
    And validate DELETE status code 201 CREATED