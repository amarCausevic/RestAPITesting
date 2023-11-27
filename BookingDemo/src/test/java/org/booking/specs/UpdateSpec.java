package org.booking.specs;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.booking.actions.CreateDAO;
import org.booking.actions.UpdateDAO;
import org.booking.model.CreateResponseDTO;

public class UpdateSpec extends UpdateDAO {

  private static CreateResponseDTO createResponseDTO;

  @Before("@createBooking")
  public static void testPrerequisites() {
    Response createResponse = CreateDAO.createBooking();
    createResponseDTO = CreateDAO.getCreateResponse(createResponse);
  }

  @Given("payload, user sends a request to the endpoint")
  public static void userUpdatesBooking() {
    Response response = updateBooking(String.valueOf(createResponseDTO.getBookingId()));
  }
}
