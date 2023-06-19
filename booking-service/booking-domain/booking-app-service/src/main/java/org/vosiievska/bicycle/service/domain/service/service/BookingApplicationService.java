package org.vosiievska.bicycle.service.domain.service.service;

import org.vosiievska.bicycle.service.domain.core.event.BookingCanceledEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingCreatedEvent;
import org.vosiievska.bicycle.service.domain.service.dto.request.CreateBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.request.DeclineBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;

public interface BookingApplicationService {

  BookingCreatedEvent createBooking(CreateBookingRequest request);

  BookingCanceledEvent cancelBooking(DeclineBookingRequest request);

  BookingStatusResponse getBookingStatus(BookingId bookingId);
}
