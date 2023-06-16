package org.vosiievska.bicycle.service.repository;

import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.entity.Booking;

import java.util.Optional;

/**
 * Secondary port according to the Hexagonal Architecture
 */
public interface BookingRepository {

  Optional<Booking> saveBooking(Booking booking);

  Optional<BookingStatusResponse> findBookingStatusById(BookingId bookingId);

}
