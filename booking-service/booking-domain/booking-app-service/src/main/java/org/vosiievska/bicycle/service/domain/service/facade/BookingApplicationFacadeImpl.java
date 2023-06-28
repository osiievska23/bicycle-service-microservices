package org.vosiievska.bicycle.service.domain.service.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vosiievska.bicycle.service.domain.core.entity.Booking;
import org.vosiievska.bicycle.service.domain.core.event.BookingCanceledEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingCreatedEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingPaidEvent;
import org.vosiievska.bicycle.service.domain.event.DomainEventPublisher;
import org.vosiievska.bicycle.service.domain.service.dto.request.CreateBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.request.DeclineBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.domain.service.dto.response.PaymentResponse;
import org.vosiievska.bicycle.service.domain.service.exception.BookingEventPublisherException;
import org.vosiievska.bicycle.service.domain.service.mapper.BookingMapper;
import org.vosiievska.bicycle.service.domain.service.service.BookingApplicationService;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;

import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookingApplicationFacadeImpl implements BookingApplicationFacade {

  private final BookingApplicationService bookingApplicationService;
  private final Set<DomainEventPublisher<? extends BookingEvent>> domainEventPublisher;
  private final BookingMapper bookingMapper;

  @Override
  public BookingStatusResponse createBooking(CreateBookingRequest request) {
    BookingCreatedEvent bookingCreatedEvent = bookingApplicationService.createBooking(request);
    publishBookingEvent(bookingCreatedEvent);
    log.info("Published 'BookingCreatedEvent' for booking with id '{}'", bookingCreatedEvent.getDomain().getIdValue());
    return bookingMapper.bookingToBookingStatusResponse(bookingCreatedEvent.getDomain());
  }

  @Override
  public BookingStatusResponse cancelBooking(DeclineBookingRequest request) {
    BookingCanceledEvent bookingCanceledEvent = bookingApplicationService.cancelBooking(request);
    publishBookingEvent(bookingCanceledEvent);
    log.info("Published 'BookingCanceledEvent' for booking with id '{}'", bookingCanceledEvent.getDomain().getIdValue());
    return bookingMapper.bookingToBookingStatusResponse(bookingCanceledEvent.getDomain());
  }

  @Override
  public BookingStatusResponse getBookingStatusById(BookingId bookingId) {
    return bookingApplicationService.getBookingStatus(bookingId);
  }

  @Override
  public void approveBookingByWorkshop(PaymentResponse paymentResponse) {
    BookingId bookingId = new BookingId(UUID.fromString(paymentResponse.getBookingId()));
    Booking booking = bookingApplicationService.getBookingById(bookingId);
    publishBookingEvent(new BookingPaidEvent(booking));
  }

  @SuppressWarnings("unchecked")
  private void publishBookingEvent(BookingEvent event) {
    domainEventPublisher.stream()
        .map(p -> (DomainEventPublisher<BookingEvent>) p)
        .filter(p -> p.supports(event))
        .findFirst()
        .orElseThrow(() -> new BookingEventPublisherException(
            "Booking domain event publisher supporting %s event not found", event.getClass().getSimpleName()))
        .publish(event);
  }
}
