Feature: User creates a new booking providing correct payload and targeting correct endpoint

  Scenario: As user I want to create new booking
    Given user sends a CREATE request to the endpoint
    When endpoint is reached, new booking is added to database
    Then validate correct values are added to database
    And validate create status code 200