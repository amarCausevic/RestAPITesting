package org.booking.specs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.booking.actions.BookingsDAO;
import org.booking.actions.CreateDAO;
import org.booking.model.BookingDetailDTO;
import org.booking.model.BookingsDTO;
import org.booking.model.CreateResponseDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class BookingsSpec extends BookingsDAO {

  /*
   * You will need to create a new booking
   * after that you will need to asset that correct values are
   * fetched from api!!!!*/
  private static BookingsDTO responseDTO;
  private static BookingDetailDTO responseDetailDTO;
  private static Response createResponse;
  private static Response getAllBookings;
  private static Response getOneBooking;
  private static CreateResponseDTO createResponseDTO;
  private static final Logger logger = LogManager.getLogger(BookingsSpec.class);


  @Before
  public static void testPrerequisites() {
    createResponse = CreateDAO.createBooking();
    createResponseDTO = CreateDAO.getCreateResponse(createResponse);
    logger.info("Before hook is executed");
  }

  @Given("user sends a request to the endpoint")
  public static void getAllBookings() {
    getAllBookings = getBookings();

    responseDTO = getResponse(getAllBookings);
  }

  @When("endpoint is reached, all bookings will be retried")
  public static void validateResponse() {
    Assert.assertNotNull(responseDTO);
  }

  @Then("validate response object contains bookingid property")
  public static void validateResponseProperty() {
    Assert.assertNotNull(responseDTO.getBookingIds().get(0).getBookingId());
  }

  @And("validate GET ALL booking status code {int}")
  public static void validateEndStatusCode(int defaultStatusCode) {
    assertStatusCode(getAllBookings, defaultStatusCode);
  }

  @Given("user sends a request to the endpoint for a specific booking")
  public static void getBookingDetails() {
    testPrerequisites();
    logger.info("New Booking Created, ID: {}", createResponseDTO.getBookingId());

    getOneBooking = getBooking(String.valueOf(createResponseDTO.getBookingId()));
    logger.info("Retrieving booking details ID: {}",
        getOneBooking.then().extract().body().asString());
    responseDetailDTO = getDetailResponse(getOneBooking);
  }

  @When("endpoint is reached, specific booking will be retried")
  public static void validateResponseDetail() {
    Assert.assertNotNull(responseDetailDTO);
  }

  @Then("validate response object contains booking detail object")
  public static void validateObjectProperties() {
    validateRetrievedBookingDetails(responseDetailDTO);
  }

  @And("validate GET ONE booking status code {int}")
  public static void validateGetOneEndStatusCode(int defaultStatusCode) {
    assertStatusCode(getOneBooking, defaultStatusCode);
  }
}
