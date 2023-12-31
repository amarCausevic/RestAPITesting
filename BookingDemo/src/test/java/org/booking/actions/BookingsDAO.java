package org.booking.actions;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.HashMap;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.booking.enums.Base;
import org.booking.enums.EndPoint;
import org.booking.model.BookingDetailDTO;
import org.booking.model.BookingsDTO;
import org.junit.Assert;
import utils.actions.RestBuilderDAO;
import utils.enums.StatusCode;
import utils.model.BaseUtil;
import utils.model.RequestBodyDTO;
import utils.model.ResponseBodyDTO;

public class BookingsDAO extends RestBuilderDAO {

  private static final String baseURI = Base.BASE_URI.val();
  private static final String emptyString = BaseUtil.EMPTY_STRING.val();
  private static final String bookingsEndPoint = EndPoint.BOOKING.val();
  private static final String singleBookingEndPoint = EndPoint.SINGLE_BOOKING.val();
  private static final int statusOK = StatusCode.OK.val();
  private static final HashMap<String, String> pathParam = new HashMap<>();
  private static final Logger logger = LogManager.getLogger(BookingsDAO.class);

  public static RequestBodyDTO requestPayload(String id) {
    pathParam.put("id", id);
    HashMap<String, String> pathParams = id.isEmpty() ? emptyHashMap() : pathParam;

    return new RequestBodyDTO(baseURI, emptyHashMap(), emptyHashMap(), pathParams,
        emptyHashMap(), ContentType.JSON, emptyObject());
  }

  public static ResponseBodyDTO responsePayload(Boolean singleBooking) {
    String endPoint = singleBooking ? singleBookingEndPoint : bookingsEndPoint;

    return new ResponseBodyDTO(Method.GET, endPoint, statusOK);
  }

  public static Response getBookings() {
    return responseApi(requestPayload(emptyString), responsePayload(false), false);
  }

  public static Response getBooking(String id) {
    return responseApi(requestPayload(id), responsePayload(true), false);
  }

  public static BookingsDTO getResponse(Response response) {
    JsonPath body = extractResponseAsJSON(response);

    return body.getObject(emptyString, BookingsDTO.class);
  }

  public static BookingDetailDTO getDetailResponse(Response response) {
    JsonPath body = extractResponseAsJSON(response);

    return body.getObject(emptyString, BookingDetailDTO.class);
  }

  public static void validateRetrievedBookingDetails(BookingDetailDTO responseBody) {
    assertBooking(CreateDAO.payload(), responseBody);
  }

  public static void assertBooking(BookingDetailDTO expected, BookingDetailDTO actual) {
    try {
      if (ObjectUtils.isNotEmpty(expected) && ObjectUtils.isNotEmpty(actual)) {
        Assert.assertTrue(expected.equals(actual));
      }
    } catch (AssertionError assertionError) {
      logger.error("Please check the sent payload, values are not correct");
      throw new RuntimeException(assertionError);
    }
  }
}
