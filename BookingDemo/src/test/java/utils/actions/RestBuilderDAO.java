package utils.actions;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import utils.model.RequestBodyDTO;
import utils.model.ResponseBodyDTO;

public class RestBuilderDAO extends RestAssured {

  private static RequestSpecBuilder builder() {
    return new RequestSpecBuilder();
  }

  public static RequestSpecification buildRequestBody(RequestBodyDTO bodyDTO) {
    return builder().setBaseUri(bodyDTO.getBaseUri())
        .addParams(bodyDTO.getParams())
        .addQueryParams(bodyDTO.getQueryParams())
        .addPathParams(bodyDTO.getPathParams())
        .addHeaders(bodyDTO.getHeaders())
        .setContentType(bodyDTO.getContentType())
        .setBody(bodyDTO.getBodyDTO())
        .build();

  }

  public static ValidatableResponse assertStatusCode(Response response, int code) {
    return response.then().assertThat().statusCode(code);
  }

  public static JsonPath extractResponseAsJSON(Response response) {
    return response.then().extract().body().jsonPath();
  }

  public static String extractResponseAsString(Response response) {
    return response.then().extract().response().body().asString();
  }

  public static Response responseApi(RequestBodyDTO bodyDTO, ResponseBodyDTO responseBodyDTO) {
    return given()
        .spec(buildRequestBody(bodyDTO)).log().all()
        .when()
        .log().all()
        .request(responseBodyDTO.getMethod(), responseBodyDTO.getEndPoint());
  }

  public static HashMap<String, String> emptyHashMap() {
    return new HashMap<>();
  }

  public static Object emptyObject() {
    return new Object();
  }
}
