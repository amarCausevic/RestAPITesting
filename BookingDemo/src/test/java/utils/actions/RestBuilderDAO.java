package utils.actions;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.model.RequestBodyDTO;
import utils.model.ResponseBodyDTO;

public class RestBuilderDAO extends RestAssured {

  private static RequestSpecBuilder builder() {
    return new RequestSpecBuilder();
  }

  private static final Logger logger = LogManager.getLogger(RestAssured.class);


  //TODO: Optimize issue where EMPTY OBJECT CANNOT BE SET AS .setBody() parameter!!! Deserialization issue!!!
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

  //TODO: Optimize issue where EMPTY OBJECT CANNOT BE SET AS .setBody() parameter!!! Deserialization issue!!!
  public static RequestSpecification buildRequestBodyWithoutSetBody(RequestBodyDTO bodyDTO) {
    return builder().setBaseUri(bodyDTO.getBaseUri())
        .addParams(bodyDTO.getParams())
        .addQueryParams(bodyDTO.getQueryParams())
        .addPathParams(bodyDTO.getPathParams())
        .addHeaders(bodyDTO.getHeaders())
        .setContentType(bodyDTO.getContentType())
        .build();
  }

  public static ValidatableResponse assertStatusCode(Response response, int code) {
    int statusCode = response.getStatusCode();

    try {
      logger.info("Validation of response status was SUCCESS! Status code was: {}", statusCode);

      return response.then().assertThat().statusCode(code);
    } catch (AssertionError assertionError) {
      logger.info("Validation of response status was FAILED! Status code was: {}", statusCode);

      throw new RuntimeException(assertionError);
    }
  }

  public static JsonPath extractResponseAsJSON(Response response) {
    return response.then().extract().body().jsonPath();
  }

  public static String extractResponseAsString(Response response) {
    return response.then().extract().response().body().asString();
  }

  public static Response responseApi(RequestBodyDTO bodyDTO, ResponseBodyDTO responseBodyDTO,
      Boolean setBody) {
    RequestSpecification requestSpec = setBody ?
        buildRequestBody(bodyDTO) :
        buildRequestBodyWithoutSetBody(bodyDTO);

    return given()
        .spec(requestSpec).log().all()
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
