package utils.model;

import io.restassured.http.ContentType;
import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBodyDTO {

  private String baseUri;
  private HashMap<String, String> params;
  private HashMap<String, String> queryParams;
  private HashMap<String, String> pathParams;
  private HashMap<String, String> headers;
  private ContentType contentType;
  private Object bodyDTO;
}
