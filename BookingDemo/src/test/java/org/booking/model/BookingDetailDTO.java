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
}
