package org.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetailDTO {

  @JsonProperty("firstname")
  private String firstName;
  @JsonProperty("lastname")
  private String lastname;
  @JsonProperty("totalprice")
  private int totalPrice;
  @JsonProperty("depositpaid")
  private Boolean depositPaid;
  @JsonProperty("bookingdates")
  private BookingDatesDTO bookingDates;
  @JsonProperty("additionalneeds")
  private String additionalNeeds;

  @Override
  public boolean equals(Object obj) {
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }

    return Objects.equals(((BookingDetailDTO) obj).getFirstName(), this.firstName)
        && Objects.equals(((BookingDetailDTO) obj).getLastname(), this.lastname)
        && Objects.equals(((BookingDetailDTO) obj).getTotalPrice(), this.totalPrice)
        && Objects.equals(((BookingDetailDTO) obj).getDepositPaid(), this.depositPaid)
        && ((BookingDetailDTO) obj).getBookingDates().equals(this.getBookingDates())
        && Objects.equals(((BookingDetailDTO) obj).getAdditionalNeeds(), this.additionalNeeds);
  }
}
