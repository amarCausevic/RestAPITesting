package org.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {

  @JsonProperty
  private String token;
  @JsonIgnoreProperties(ignoreUnknown = true)
  private String reason;
}
