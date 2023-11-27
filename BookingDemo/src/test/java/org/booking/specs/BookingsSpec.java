package org.booking.specs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import org.booking.actions.BookingsDAO;
import org.booking.model.BookingDetailDTO;
import org.booking.model.BookingsDTO;
import org.junit.Assert;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class BookingsSpec extends BookingsDAO {

  /*
   * You will need to create a new booking
   * after that you will need to asset that correct values are
   * fetched from api!!!!*/
  private static BookingsDTO responseDTO = new BookingsDTO();
  private static BookingDetailDTO responseDetailDTO;

  @Given("user sends a request to the endpoint")
  public static BookingsDTO getAllBookings() {
    Response response = getBookings();
    responseDTO = getResponse(response);

    return responseDTO;
  }

  @When("endpoint is reached, all bookings will be retried")
  public static void validateResponse() {
    Assert.assertNotNull(responseDTO);
  }

  @Then("validate response object contains bookingid property")
  public static void validateResponseProperty() {
    Assert.assertNotNull(responseDTO.getBookingId());
  }

  @Given("user sends a request to the endpoint for a specific booking")
  public static BookingDetailDTO getBookingDetails() {
    Response responseDetail = getBooking(responseDTO.getBookingId());
    responseDetailDTO = getDetailResponse(responseDetail);

    return responseDetailDTO;
  }

  @When("endpoint is reached,specific booking will be retried")
  public static void validateResponseDetail() {
    Assert.assertNotNull(responseDetailDTO);
  }

  @Then("validate response object contains booking detail object")
  public static void validateObjectProperties() {
    Assert.assertNotNull(responseDetailDTO.getLastname());
  }

  @And("validate GET status code {int}")
  public static void validateEndStatusCode(int defaultStatusCode) {
    //Change this get value from response!
    Assert.assertEquals(defaultStatusCode, 200);
  }
}
