Feature: Validating CORRECT endpoint responses PUT details booking

  @createBooking
  Scenario: As user I want to update may booking entry
    Given payload, user sends a request to the endpoint
    When endpoint is reached, booking details are changed
    Then validate response object contains new data
    And validate PUT ALL booking status code 200