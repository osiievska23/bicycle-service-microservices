package org.vosiievska.bicycle.service.facade;

import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.dto.request.CreateBookingRequest;
import org.vosiievska.bicycle.service.dto.request.DeclineBookingRequest;
import org.vosiievska.bicycle.service.dto.response.BookingStatusResponse;

public interface BookingApplicationFacade {

  BookingStatusResponse createBooking(CreateBookingRequest request);

  BookingStatusResponse declineBooking(DeclineBookingRequest bookingId);

  BookingStatusResponse getBookingStatusById(BookingId bookingId);

}
