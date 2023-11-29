package org.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.Objects;
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

  @Override
  public boolean equals(Object obj) {
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }

    return Objects.equals(((BookingDatesDTO) obj).getCheckIn(), this.checkIn)
        && Objects.equals(((BookingDatesDTO) obj).getCheckOut(), this.checkOut);
  }
}
