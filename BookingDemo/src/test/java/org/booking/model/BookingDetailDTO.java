package org.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDetailDTO {

  @JsonProperty("firstname")
  private String firstName;
  @JsonProperty("lastName")
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
