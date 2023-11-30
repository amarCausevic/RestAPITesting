package org.booking.actions;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.booking.enums.Base;
import org.booking.enums.EndPoint;
import org.junit.Assert;
import utils.actions.RestBuilderDAO;
import utils.enums.StatusCode;
import utils.model.BaseUtil;
import utils.model.RequestBodyDTO;
import utils.model.ResponseBodyDTO;

public class DeleteDAO extends RestBuilderDAO {

  private static final String baseURI = Base.BASE_URI.val();
  private static final String emptyString = BaseUtil.EMPTY_STRING.val();
  private static final String endPoint = EndPoint.SINGLE_BOOKING.val();
  private static final int statusCreated = StatusCode.CREATED.val();
  private static final int statusNotFound = StatusCode.NOT_FOUND.val();
  private static final Logger logger = LogManager.getLogger(DeleteDAO.class);
  private static final HashMap<String, String> pathParam = new HashMap<>();
  private static final HashMap<String, String> headers = new HashMap<>();

  public static RequestBodyDTO requestPayload(String id, String token) {
    pathParam.put("id", id);
    headers.put("Cookie", "token=" + token);

    return new RequestBodyDTO(baseURI, emptyHashMap(), emptyHashMap(), pathParam,
        headers, ContentType.JSON, emptyObject());
  }

  public static ResponseBodyDTO responsePayload() {
    return new ResponseBodyDTO(Method.DELETE, endPoint, statusCreated);
  }

  public static Response deleteBooking(String id, String token) {
    return responseApi(requestPayload(id, token), responsePayload(), false);
  }

  public static void validateResponseBody(String expected, String actual) {
    Assert.assertEquals(expected, actual);
  }

  public static void validateBookingWasDeleted(String id, String expectedResponseBodyMsg) {
    Response getBooking = BookingsDAO.getBooking(id);
    String actualResponseBodyMsg = extractResponseAsString(getBooking);

    Assert.assertEquals(expectedResponseBodyMsg, actualResponseBodyMsg);
    assertStatusCode(getBooking, statusNotFound);
  }
}
