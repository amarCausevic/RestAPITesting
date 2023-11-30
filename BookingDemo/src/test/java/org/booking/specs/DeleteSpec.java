package org.booking.specs;


import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.booking.actions.CreateDAO;
import org.booking.actions.DeleteDAO;
import org.booking.actions.LoginDAO;
import org.booking.model.CreateResponseDTO;
import org.booking.model.LoginResponseDTO;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class DeleteSpec extends DeleteDAO {

  private static CreateResponseDTO createResponseDTO;
  private static LoginResponseDTO loginResponseDTO;
  private static Response deleteResponse;
  private static String bookingId = "";

  private static final Logger logger = LogManager.getLogger(DeleteDAO.class);

  //TODO: This is the same as in UPDATE SPEC!!!!
  @Before
  public static void testPrerequisites() {
    Response loginResponse = LoginDAO.login("admin", "password123");
    Response createResponse = CreateDAO.createBooking();

    createResponseDTO = CreateDAO.getCreateResponse(createResponse);
    loginResponseDTO = LoginDAO.getResponse(loginResponse);
  }

  @Given("user sends a DELETE request to the endpoint")
  public static void userDeletesBooking() {
    bookingId = String.valueOf(createResponseDTO.getBookingId());
    String token = loginResponseDTO.getToken();

    System.out.println("Booking ID: " + bookingId);
    deleteResponse = deleteBooking(bookingId, token);
  }

  @When("endpoint is reached, validate {string} is returned into the response body")
  public static void validateResponseBody(String expectedResponseBody) {
    String actualResponseBody = extractResponseAsString(deleteResponse);

    validateResponseBody(expectedResponseBody, actualResponseBody);
  }

  @Then("users triggers search for deleted BOOKING ID, in response users gets {string}")
  public static void validateBookingDoesNotExists(String expectedResponseBodyMsg) {
    validateBookingWasDeleted(bookingId, expectedResponseBodyMsg);
  }

  @And("validate DELETE status code {int} CREATED")
  public static void validateDeleteResponseStatusCode(int expectedStatusCode) {
    assertStatusCode(deleteResponse, expectedStatusCode);
  }
}
