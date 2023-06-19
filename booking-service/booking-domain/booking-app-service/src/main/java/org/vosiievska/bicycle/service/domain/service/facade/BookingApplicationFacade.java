package org.vosiievska.bicycle.service.domain.service.facade;

import org.vosiievska.bicycle.service.domain.service.dto.request.CreateBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.request.DeclineBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;

public interface BookingApplicationFacade {

  BookingStatusResponse createBooking(CreateBookingRequest request);

  BookingStatusResponse cancelBooking(DeclineBookingRequest bookingId);

  BookingStatusResponse getBookingStatusById(BookingId bookingId);

}
