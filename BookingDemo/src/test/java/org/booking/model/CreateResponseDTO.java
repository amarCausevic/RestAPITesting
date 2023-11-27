package org.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateResponseDTO {

  @JsonProperty("bookingid")
  private int bookingId;
  @JsonProperty("booking")
  private BookingDetailDTO booking;
}
