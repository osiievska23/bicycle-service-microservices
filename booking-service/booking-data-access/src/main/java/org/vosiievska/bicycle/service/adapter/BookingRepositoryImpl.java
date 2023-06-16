package org.vosiievska.bicycle.service.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.vosiievska.bicycle.service.domain.exception.EntityNotFoundException;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.dto.interfaces.BookingStatusInterface;
import org.vosiievska.bicycle.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.entity.Booking;
import org.vosiievska.bicycle.service.entity.BookingEntity;
import org.vosiievska.bicycle.service.mapper.BookingMapper;
import org.vosiievska.bicycle.service.repository.BookingJpaRepository;
import org.vosiievska.bicycle.service.repository.BookingRepository;

import java.util.Optional;

/**
 * Adapter connected to the secondary port
 */
@Component
@RequiredArgsConstructor
public class BookingRepositoryImpl implements BookingRepository {

  private final BookingJpaRepository bookingJpaRepository;
  private final BookingMapper bookingMapper;

  @Override
  public Booking saveBooking(Booking booking) {
    BookingEntity savedEntity = bookingJpaRepository.save(bookingMapper.bookingToJpaEntity(booking));
    return bookingMapper.jpaEntityToBooking(savedEntity);
  }

  @Override
  public Optional<BookingStatusResponse> findBookingStatusById(BookingId bookingId) {
    BookingStatusInterface bookingStatusInterface = bookingJpaRepository.findBookingStatusById(bookingId.getValue())
        .orElseThrow(() -> new EntityNotFoundException("Booking by id '%s' not found", bookingId.getValue()));
    return Optional.ofNullable(bookingMapper.jpaEntityToBookingStatus(bookingStatusInterface));
  }
}
