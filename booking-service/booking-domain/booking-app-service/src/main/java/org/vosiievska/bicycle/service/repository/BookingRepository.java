package org.vosiievska.bicycle.service.repository;

import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;
import org.vosiievska.bicycle.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.entity.Booking;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Secondary port according to the Hexagonal Architecture
 */
public interface BookingRepository {

  Booking saveBooking(Booking booking);

  Optional<BookingStatusResponse> findBookingStatusById(BookingId bookingId);

  Optional<Booking> findById(BookingId bookingId);

  void updateStatusById(BookingId bookingId, BookingStatus bookingStatus);
}
