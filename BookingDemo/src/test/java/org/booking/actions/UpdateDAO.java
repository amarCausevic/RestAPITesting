package org.booking.actions;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import java.time.LocalDate;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.booking.enums.Base;
import org.booking.enums.EndPoint;
import org.booking.model.BookingDatesDTO;
import org.booking.model.BookingDetailDTO;
import utils.actions.RestBuilderDAO;
import utils.enums.StatusCode;
import utils.model.BaseUtil;
import utils.model.RequestBodyDTO;
import utils.model.ResponseBodyDTO;

public class UpdateDAO extends RestBuilderDAO {

  private static final String baseURI = Base.BASE_URI.val();
  private static final String emptyString = BaseUtil.EMPTY_STRING.val();
  private static final String singleBookingEndPoint = EndPoint.SINGLE_BOOKING.val();
  private static final int statusOK = StatusCode.OK.val();
  private static final HashMap<String, String> pathParam = new HashMap<>();
  private static final HashMap<String, String> headers = new HashMap<>();
  private static final Logger logger = LogManager.getLogger(BookingsDAO.class);


  private static BookingDatesDTO updateDatePayload() {
    LocalDate checkIn = LocalDate.of(2022, 10, 17);
    LocalDate checkOut = LocalDate.of(2023, 10, 18);

    return new BookingDatesDTO(checkIn, checkOut);
  }

  private static BookingDatesDTO cmar() {
    LocalDate checkIn = LocalDate.of(1999, 10, 17);
    LocalDate checkOut = LocalDate.of(2005, 10, 18);

    return new BookingDatesDTO(checkIn, checkOut);
  }

  //This should be passed from CreateBooking.feature file!!!!
  public static BookingDetailDTO updatePayload() {
    return new BookingDetailDTO(
        "TestUpdated",
        "EndPointUpdatedTheTest",
        9000,
        false,
        updateDatePayload(),
        "User needs to pay additional fees to Automation");
  }

  public static RequestBodyDTO requestPayload(String id, String token) {
    pathParam.put("id", id);
    headers.put("Cookie", "token=" + token);

    return new RequestBodyDTO(baseURI, emptyHashMap(), emptyHashMap(), pathParam,
        headers, ContentType.JSON, updatePayload());
  }

  public static ResponseBodyDTO responsePayload() {
    return new ResponseBodyDTO(Method.PUT, singleBookingEndPoint, statusOK);
  }

  public static Response updateBooking(String id, String token) {
    return responseApi(requestPayload(id, token), responsePayload(), true);
  }

  public static void validateBookingResponseBody(BookingDetailDTO responseBody) {
    BookingsDAO.assertBooking(updatePayload(), responseBody);
  }
}
