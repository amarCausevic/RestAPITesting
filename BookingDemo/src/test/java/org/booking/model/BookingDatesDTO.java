package org.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDatesDTO {

  @JsonProperty("checkin")
  private Date checkIn;
  @JsonProperty("checkout")
  private Date checkOut;
}
