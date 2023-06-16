package org.vosiievska.bicycle.service.service;

import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.dto.request.CreateBookingRequest;
import org.vosiievska.bicycle.service.dto.request.DeclineBookingRequest;
import org.vosiievska.bicycle.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.event.BookingCanceledEvent;
import org.vosiievska.bicycle.service.event.BookingCreatedEvent;

public interface BookingApplicationService {

  BookingCreatedEvent createBooking(CreateBookingRequest request);

  BookingCanceledEvent cancelBooking(DeclineBookingRequest request);

  BookingStatusResponse getBookingStatus(BookingId bookingId);
}
