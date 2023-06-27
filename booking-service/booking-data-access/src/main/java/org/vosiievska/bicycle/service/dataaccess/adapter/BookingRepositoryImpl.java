package org.vosiievska.bicycle.service.dataaccess.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.vosiievska.bicycle.service.dataaccess.jpa.entity.BookingEntity;
import org.vosiievska.bicycle.service.dataaccess.interfaces.BookingStatusInterface;
import org.vosiievska.bicycle.service.dataaccess.mapper.BookingJpaMapper;
import org.vosiievska.bicycle.service.dataaccess.repository.BookingJpaRepository;
import org.vosiievska.bicycle.service.domain.core.entity.Booking;
import org.vosiievska.bicycle.service.domain.exception.EntityNotFoundException;
import org.vosiievska.bicycle.service.domain.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.domain.service.repository.BookingRepository;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;

import java.util.Optional;

/**
 * Adapter connected to the secondary port
 */
@Component
@Transactional
@RequiredArgsConstructor
public class BookingRepositoryImpl implements BookingRepository {

  private final BookingJpaRepository bookingJpaRepository;
  private final BookingJpaMapper bookingJpaMapper;

  @Override
  public Booking saveBooking(Booking booking) {
    BookingEntity savedEntity = bookingJpaRepository.save(bookingJpaMapper.bookingToJpaEntity(booking));
    return bookingJpaMapper.jpaEntityToBooking(savedEntity);
  }

  @Override
  public Optional<BookingStatusResponse> findBookingStatusById(BookingId bookingId) {
    BookingStatusInterface bookingStatusInterface = bookingJpaRepository.findBookingStatusById(bookingId.getValue())
        .orElseThrow(() -> new EntityNotFoundException("Booking by id '%s' not found", bookingId.getValue()));
    return Optional.ofNullable(bookingJpaMapper.jpaEntityToBookingStatus(bookingStatusInterface));
  }

  @Override
  public Optional<Booking> findById(BookingId bookingId) {
    return bookingJpaRepository.findById(bookingId.getValue())
        .map(bookingJpaMapper::jpaEntityToBooking);
  }

  @Override
  public void updateStatusById(BookingId bookingId, BookingStatus bookingStatus) {
    bookingJpaRepository.updateStatusById(bookingId.getValue(), bookingStatus.toString());
  }
}
