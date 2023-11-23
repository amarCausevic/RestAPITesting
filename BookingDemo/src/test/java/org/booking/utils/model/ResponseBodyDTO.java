package org.booking.utils.model;

import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBodyDTO {

  private Method method;
  private String uri;
  private int statusCode;
  private RequestSpecification requestBody;
}
