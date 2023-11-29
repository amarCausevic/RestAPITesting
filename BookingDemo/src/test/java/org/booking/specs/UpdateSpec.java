package org.booking.specs;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.booking.actions.BookingsDAO;
import org.booking.actions.CreateDAO;
import org.booking.actions.LoginDAO;
import org.booking.actions.UpdateDAO;
import org.booking.model.BookingDetailDTO;
import org.booking.model.CreateResponseDTO;
import org.booking.model.LoginResponseDTO;
import org.junit.Assert;

public class UpdateSpec extends UpdateDAO {

  private static CreateResponseDTO createResponseDTO;
  private static LoginResponseDTO loginResponseDTO;
  private static BookingDetailDTO bookingDetailDTO;
  private static Response updateRequest;

  @Before()
  public static void testPrerequisites() {
    Response loginResponse = LoginDAO.login("admin", "password123");
    Response createResponse = CreateDAO.createBooking();

    createResponseDTO = CreateDAO.getCreateResponse(createResponse);
    loginResponseDTO = LoginDAO.getResponse(loginResponse);
  }

  @Given("payload, user sends a request to the endpoint")
  public static void userUpdatesBooking() {
    String id = String.valueOf(createResponseDTO.getBookingId());
    String token = loginResponseDTO.getToken();
    updateRequest = updateBooking(id, token);

    bookingDetailDTO = BookingsDAO.getDetailResponse(updateRequest);
  }

  @When("endpoint is reached, booking details are changed")
  public static void validateResponseIsNotNull() {
    Assert.assertNotNull(bookingDetailDTO);
  }

  @Then("validate response object contains new data")
  public static void validateResponseObjectWasUpdated() {
    validateBookingResponseBody(bookingDetailDTO);
  }

  @And("validate PUT ALL booking status code {int}")
  public static void test4(int defaultStatusCode) {
    assertStatusCode(updateRequest, defaultStatusCode);
  }
}
