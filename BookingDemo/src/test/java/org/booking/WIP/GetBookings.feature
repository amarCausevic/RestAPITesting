Feature: Validating CORRECT endpoint responses GET booking and booking/id

  Scenario: As user I want to retrieve all booking
    Given user sends a request to the endpoint
    When endpoint is reached, all bookings will be retried
    Then validate response object contains bookingid property
    And validate GET status code 200

  Scenario: As user I want to retrieve a specific booking
    Given user sends a request to the endpoint for a specific booking
    When endpoint is reached,specific booking will be retried
    Then validate response object contains booking detail object
    And validate GET status code 200