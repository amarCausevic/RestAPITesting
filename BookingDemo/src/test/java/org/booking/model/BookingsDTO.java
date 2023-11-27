package org.booking.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingsDTO {

  @JsonProperty
  private List<BookingsResponseDTO> bookingIds;


  @JsonCreator
  public BookingsDTO(List<BookingsResponseDTO> id) {
    this.bookingIds = id;
  }
}
