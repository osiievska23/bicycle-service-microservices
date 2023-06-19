package org.vosiievska.bicycle.service.domain.service.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vosiievska.bicycle.service.domain.core.event.BookingCanceledEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingCreatedEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingEvent;
import org.vosiievska.bicycle.service.domain.event.DomainEventPublisher;
import org.vosiievska.bicycle.service.domain.service.dto.request.CreateBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.request.DeclineBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.domain.service.mapper.BookingMapper;
import org.vosiievska.bicycle.service.domain.service.service.BookingApplicationService;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingApplicationFacadeImpl implements BookingApplicationFacade {

  private final BookingApplicationService bookingApplicationService;
  private final DomainEventPublisher<BookingEvent> domainEventPublisher;
  private final BookingMapper bookingMapper;

  @Override
  public BookingStatusResponse createBooking(CreateBookingRequest request) {
    BookingCreatedEvent bookingCreatedEvent = bookingApplicationService.createBooking(request);
    domainEventPublisher.publish(bookingCreatedEvent);
    log.info("Published 'BookingCreatedEvent' for booking with id '{}'", bookingCreatedEvent.getDomain().getIdValue());
    return bookingMapper.bookingToBookingStatusResponse(bookingCreatedEvent.getDomain());
  }

  @Override
  public BookingStatusResponse cancelBooking(DeclineBookingRequest request) {
    BookingCanceledEvent bookingCanceledEvent = bookingApplicationService.cancelBooking(request);
    domainEventPublisher.publish(bookingCanceledEvent);
    log.info("Published 'BookingCanceledEvent' for booking with id '{}'", bookingCanceledEvent.getDomain().getIdValue());
    return bookingMapper.bookingToBookingStatusResponse(bookingCanceledEvent.getDomain());
  }

  @Override
  public BookingStatusResponse getBookingStatusById(BookingId bookingId) {
    return bookingApplicationService.getBookingStatus(bookingId);
  }
}
