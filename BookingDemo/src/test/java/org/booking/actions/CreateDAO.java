package org.booking.actions;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.time.LocalDate;
import org.booking.enums.Base;
import org.booking.enums.EndPoint;
import org.booking.model.BookingDatesDTO;
import org.booking.model.BookingDetailDTO;
import org.booking.model.CreateResponseDTO;
import utils.enums.StatusCode;
import utils.model.BaseUtil;
import utils.model.RequestBodyDTO;
import utils.model.ResponseBodyDTO;

public class CreateDAO extends BookingsDAO {

  private static final String baseURI = Base.BASE_URI.val();
  private static final String emptyString = BaseUtil.EMPTY_STRING.val();
  private static final String endPoint = EndPoint.BOOKING.val();
  private static final int statusOK = StatusCode.OK.val();

  private static BookingDatesDTO datePayload() {
    LocalDate checkIn = LocalDate.of(2018, 1, 1);
    LocalDate checkOut = LocalDate.of(2018, 1, 1);

    return new BookingDatesDTO(checkIn, checkOut);
  }

  //This should be passed from Create.feature file!!!!
  private static BookingDetailDTO payload() {
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
    return responseApi(requestPayload(), responsePayload());
  }

  public static CreateResponseDTO getCreateResponse(Response response) {
    JsonPath body = extractResponseAsJSON(response);

    return body.getObject(emptyString, CreateResponseDTO.class);
  }
}
