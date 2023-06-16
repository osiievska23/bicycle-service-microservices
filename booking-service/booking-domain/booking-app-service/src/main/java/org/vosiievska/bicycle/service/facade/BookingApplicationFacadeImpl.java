package org.vosiievska.bicycle.service.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vosiievska.bicycle.service.domain.event.DomainEventPublisher;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.dto.request.CreateBookingRequest;
import org.vosiievska.bicycle.service.dto.request.DeclineBookingRequest;
import org.vosiievska.bicycle.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.event.BookingCreatedEvent;
import org.vosiievska.bicycle.service.mapper.BookingMapper;
import org.vosiievska.bicycle.service.service.BookingApplicationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingApplicationFacadeImpl implements BookingApplicationFacade {

  private final BookingApplicationService bookingApplicationService;
  private final DomainEventPublisher domainEventPublisher;
  private final BookingMapper bookingMapper;

  @Override
  public BookingStatusResponse createBooking(CreateBookingRequest request) {
    BookingCreatedEvent bookingCreatedEvent = bookingApplicationService.createBooking(request);
    domainEventPublisher.publish(bookingCreatedEvent);
    log.info("Published 'BookingCreatedEvent' for booking with id '{}'", bookingCreatedEvent.getBooking().getIdValue());
    return bookingMapper.bookingToBookingStatusResponse(bookingCreatedEvent.getBooking());
  }

  @Override
  public BookingStatusResponse declineBooking(DeclineBookingRequest request) {
    return null;
  }

  @Override
  public BookingStatusResponse getBookingStatusById(BookingId bookingId) {
    return bookingApplicationService.getBookingStatus(bookingId);
  }
}
