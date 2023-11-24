package org.booking.actions;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.HashMap;
import org.booking.enums.Base;
import org.booking.enums.EndPoint;
import org.booking.model.BookingDetailDTO;
import org.booking.model.BookingsDTO;
import utils.actions.RestBuilderDAO;
import utils.enums.StatusCode;
import utils.model.BaseUtil;
import utils.model.RequestBodyDTO;
import utils.model.ResponseBodyDTO;

public class BookingsDAO extends RestBuilderDAO {

  private static final String baseURI = Base.BASE_URI.val();
  private static final String emptyString = BaseUtil.EMPTY_STRING.val();
  private static final String endPoint = EndPoint.BOOKING.val();
  private static final int statusOK = StatusCode.OK.val();
  private static final HashMap<String, String> pathParam = new HashMap<>();

  public static RequestBodyDTO requestPayload(String id) {
    pathParam.put("id", id);

    return new RequestBodyDTO(baseURI, emptyHashMap(), emptyHashMap(), pathParam,
        emptyHashMap(), ContentType.JSON, emptyObject());
  }

  public static ResponseBodyDTO responsePayload() {
    return new ResponseBodyDTO(Method.GET, endPoint, statusOK);
  }

  public static Response getBookings() {
    return responseApi(requestPayload(emptyString), responsePayload());
  }

  public static Response getBooking(String id) {
    return responseApi(requestPayload(id), responsePayload());
  }

  public static BookingsDTO getResponse(Response response) {
    JsonPath body = extractResponseAsJSON(response);

    return body.getObject("", BookingsDTO.class);
  }

  public static BookingDetailDTO getDetailResponse(Response response) {
    JsonPath body = extractResponseAsJSON(response);

    return body.getObject("", BookingDetailDTO.class);
  }
}
