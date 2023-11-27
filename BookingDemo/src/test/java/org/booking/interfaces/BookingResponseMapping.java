package org.booking.interfaces;

import io.restassured.response.Response;
import org.booking.model.BookingDetailDTO;

public interface BookingResponseMapping {

  BookingDetailDTO getDetailResponse(Response response);
}
