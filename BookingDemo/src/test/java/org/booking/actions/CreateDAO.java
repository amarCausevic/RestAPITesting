package org.booking.actions;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.time.LocalDate;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.booking.enums.Base;
import org.booking.enums.EndPoint;
import org.booking.model.BookingDatesDTO;
import org.booking.model.BookingDetailDTO;
import org.booking.model.CreateResponseDTO;
import org.junit.Assert;
import utils.enums.StatusCode;
import utils.model.BaseUtil;
import utils.model.RequestBodyDTO;
import utils.model.ResponseBodyDTO;

public class CreateDAO extends BookingsDAO {

  private static final String baseURI = Base.BASE_URI.val();
  private static final String emptyString = BaseUtil.EMPTY_STRING.val();
  private static final String endPoint = EndPoint.BOOKING.val();
  private static final int statusOK = StatusCode.OK.val();
  private static final Logger logger = LogManager.getLogger(CreateDAO.class);

  private static BookingDatesDTO datePayload() {
    LocalDate checkIn = LocalDate.of(2018, 1, 1);
    LocalDate checkOut = LocalDate.of(2018, 1, 1);

    return new BookingDatesDTO(checkIn, checkOut);
  }

  //This should be passed from Create.feature file!!!!
  public static BookingDetailDTO payload() {
    return new BookingDetailDTO(
        "Test",
        "CreateEndPoint",
        15000,
        true,
        datePayload(),
        "No Additional Needs");
  }

  public static RequestBodyDTO requestPayload() {
    return new RequestBodyDTO(baseURI, emptyHashMap(), emptyHashMap(), emptyHashMap(),
        emptyHashMap(), ContentType.JSON, payload());
  }

  public static ResponseBodyDTO responsePayload() {
    return new ResponseBodyDTO(Method.POST, endPoint, statusOK);
  }

  public static Response createBooking() {
    return responseApi(requestPayload(), responsePayload(), true);
  }

  public static CreateResponseDTO getCreateResponse(Response response) {
    JsonPath body = extractResponseAsJSON(response);

    return body.getObject(emptyString, CreateResponseDTO.class);
  }

  public static void validateNewBookingIsCreated(CreateResponseDTO responseDTO) {
    int bookingId = responseDTO.getBookingId();

    try {
      Assert.assertTrue(bookingId > 0);
      logger.info("Booking was added to the DB as booking id:" + bookingId);
    } catch (AssertionError exception) {
      logger.error("With with adding Booking to the DB");
      throw new AssertionError("Booking was not added to the DB");
    }
  }

  //TODO: This will need optimizing in sense: Iterate over object properties and check if values are valid!
  public static void validateCorrectBookingWasCreated(CreateResponseDTO createResponseDTO) {
    try {
      if (ObjectUtils.isNotEmpty(createResponseDTO)) {
        BookingDetailDTO bookingDetailDTO = createResponseDTO.getBooking();
        BookingDatesDTO bookingDatesDTO = createResponseDTO.getBooking().getBookingDates();

        Assert.assertEquals(payload().getFirstName(), bookingDetailDTO.getFirstName());
        Assert.assertEquals(payload().getLastname(), bookingDetailDTO.getLastname());
        Assert.assertEquals(payload().getTotalPrice(), bookingDetailDTO.getTotalPrice());
        Assert.assertEquals(payload().getDepositPaid(), bookingDetailDTO.getDepositPaid());
        Assert.assertEquals(payload().getBookingDates().getCheckIn(), bookingDatesDTO.getCheckIn());
        Assert.assertEquals(payload().getBookingDates().getCheckOut(),
            bookingDatesDTO.getCheckOut());
        Assert.assertEquals(payload().getAdditionalNeeds(), bookingDetailDTO.getAdditionalNeeds());
        logger.info("Correct data was inserted into DB");
      }
    } catch (AssertionError assertionError) {
      logger.error("Please check the sent payload, values are not correct");
      throw new RuntimeException(assertionError);
    }
  }
}
