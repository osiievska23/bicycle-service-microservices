package org.vosiievska.bicycle.service.domain.service.service;

import org.vosiievska.bicycle.service.domain.core.entity.Booking;
import org.vosiievska.bicycle.service.domain.core.event.BookingCanceledEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingCreatedEvent;
import org.vosiievska.bicycle.service.domain.service.dto.request.CancelBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.request.CreateBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.domain.service.dto.response.WorkshopResponse;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;

import java.util.List;

public interface BookingApplicationService {

  BookingCreatedEvent createBooking(CreateBookingRequest request);

  BookingCanceledEvent cancelBooking(CancelBookingRequest request);

  BookingStatusResponse getBookingStatus(BookingId bookingId);

  Booking getBookingById(BookingId bookingId);

  void updateBookingStatus(BookingId bookingId, BookingStatus bookingStatus);

  void updateBookingStatus(BookingId bookingId, BookingStatus bookingStatus, List<String> failureMessages);

  Booking updateBooking(WorkshopResponse workshopResponse);
}
