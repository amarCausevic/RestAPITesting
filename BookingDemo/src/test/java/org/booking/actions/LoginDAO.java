package org.booking.actions;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.booking.enums.Base;
import org.booking.enums.EndPoint;
import org.booking.model.LoginDTO;
import org.booking.model.LoginResponseDTO;
import utils.actions.RestBuilderDAO;
import utils.enums.StatusCode;
import utils.model.RequestBodyDTO;
import utils.model.ResponseBodyDTO;

public class LoginDAO extends RestBuilderDAO {

  private static final String baseURI = Base.BASE_URI.val();
  private static final String endPoint = EndPoint.AUTH.val();
  private static final int statusOK = StatusCode.OK.val();

  private static LoginDTO payload(String username, String password) {
    return new LoginDTO(username, password);
  }

  public static RequestBodyDTO requestPayload(String username, String password) {
    return new RequestBodyDTO(baseURI, emptyHashMap(), emptyHashMap(), emptyHashMap(),
        emptyHashMap(), ContentType.JSON, payload(username, password));
  }

  public static ResponseBodyDTO responsePayload() {
    return new ResponseBodyDTO(Method.POST, endPoint, statusOK);
  }

  public static Response login(String username, String password) {
    return responseApi(requestPayload(username, password), responsePayload());
  }

  public static LoginResponseDTO getResponse(Response response) {
    JsonPath body = extractResponseAsJSON(response);

    return body.getObject("", LoginResponseDTO.class);
  }
}
