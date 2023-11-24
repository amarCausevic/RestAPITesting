package utils.model;

import io.restassured.http.ContentType;
import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBodyDTO {

  private String baseUri;
  private HashMap<String, ?> params;
  private HashMap<String, ?> queryParams;
  private HashMap<String, ?> pathParams;
  private HashMap<String, String> headers;
  private ContentType contentType;
  private Object bodyDTO;

  public RequestBodyDTO(String baseUri, HashMap<String, ?> params, HashMap<String, ?> queryParams,
      HashMap<String, ?> pathParams, HashMap<String, String> headers, ContentType contentType,
      Object bodyDTO) {
    this.baseUri = baseUri;
    this.params = params;
    this.queryParams = queryParams;
    this.pathParams = pathParams;
    this.headers = headers;
    this.contentType = contentType;
    this.bodyDTO = bodyDTO;
  }
}
