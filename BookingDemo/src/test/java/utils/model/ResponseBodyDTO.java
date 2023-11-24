package utils.model;

import io.restassured.http.Method;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBodyDTO {

  private Method method;
  private String endPoint;
  private int statusCode;

  public ResponseBodyDTO(Method method, String endPoint, int statusCode) {
    this.method = method;
    this.endPoint = endPoint;
    this.statusCode = statusCode;
  }
}
