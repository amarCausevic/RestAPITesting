package org.booking.specs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import org.booking.actions.CreateDAO;
import org.booking.model.CreateResponseDTO;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class CreateSpec extends CreateDAO {

  private static Response response;
  private static CreateResponseDTO createResponseDTO;

  @Given("user sends a CREATE request to the endpoint")
  public static void requestCreateBooking() {
    response = createBooking();
    createResponseDTO = getCreateResponse(response);
  }

  @When("endpoint is reached, new booking is added to database")
  public static void validateResponseIsNotNull() {
    validateNewBookingIsCreated(createResponseDTO);
  }

  @Then("validate correct values are added to database")
  public static void validateResponseData() {
    validateCorrectBookingWasCreated(createResponseDTO);
  }

  @And("validate create status code {int}")
  public static void validateEndResponseStatus(int defaultStatusCode) {
    assertStatusCode(response, defaultStatusCode);
  }
}
