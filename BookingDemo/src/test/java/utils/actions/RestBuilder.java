package utils.actions;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import utils.enums.StatusCode;
import utils.model.EndPointDTO;
import utils.model.RequestBodyDTO;

public class RestBuilder extends RestAssured {

  private static RequestSpecBuilder builder() {
    return new RequestSpecBuilder();
  }

  static RequestSpecification buildRequestBody(RequestBodyDTO bodyDTO) {
    return builder().setBaseUri(bodyDTO.getBaseUri())
        .addParams(bodyDTO.getParams())
        .addQueryParams(bodyDTO.getQueryParams())
        .addPathParams(bodyDTO.getPathParams())
        .addHeaders(bodyDTO.getHeaders())
        .setContentType(bodyDTO.getContentType())
        .setBody(bodyDTO.getBodyDTO())
        .build();

  }

  static ValidatableResponse assertStatusCode(Response response, StatusCode code) {
    return response.then().assertThat().statusCode(code.val);
  }

  static JsonPath extractResponseAsJSON(ValidatableResponse assertAction) {
    return assertAction.extract().response().body().jsonPath();
  }

  static String extractResponseAsString(ValidatableResponse assertAction) {
    return assertAction.extract().response().body().asString();
  }

  static Response responseApi(EndPointDTO endPointDTO) {
    return given()
        .spec(buildRequestBody(endPointDTO.getRequestBodyDTO()))
        .when()
        .request(endPointDTO.getResponseBodyDTO().getMethod(),
            endPointDTO.getResponseBodyDTO().getUri());
  }
}
