package org.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDatesDTO {

  @JsonProperty("checkin")
  private LocalDate checkIn;
  @JsonProperty("checkout")
  private LocalDate checkOut;
}