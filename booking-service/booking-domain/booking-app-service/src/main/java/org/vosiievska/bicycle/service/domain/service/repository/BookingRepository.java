package org.vosiievska.bicycle.service.domain.service.repository;

import org.vosiievska.bicycle.service.domain.core.entity.Booking;
import org.vosiievska.bicycle.service.domain.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;

import java.util.Optional;

/**
 * Secondary port according to the Hexagonal Architecture
 */
public interface BookingRepository {

  Booking saveBooking(Booking booking);

  Optional<BookingStatusResponse> findBookingStatusById(BookingId bookingId);

  Optional<Booking> findById(BookingId bookingId);

  void updateStatusById(BookingId bookingId, BookingStatus bookingStatus);
}
