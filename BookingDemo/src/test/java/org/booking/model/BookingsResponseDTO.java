package org.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingsResponseDTO {

  @JsonProperty("bookingid")
  private String bookingId;
}
