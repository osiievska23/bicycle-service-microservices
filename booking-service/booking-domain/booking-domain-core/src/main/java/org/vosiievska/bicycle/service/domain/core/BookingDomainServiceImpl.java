package org.vosiievska.bicycle.service.domain.core;

import lombok.extern.slf4j.Slf4j;
import org.vosiievska.bicycle.service.domain.core.entity.Booking;
import org.vosiievska.bicycle.service.domain.core.entity.Client;
import org.vosiievska.bicycle.service.domain.core.entity.Workshop;
import org.vosiievska.bicycle.service.domain.core.event.BookingApprovedEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingCanceledEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingDeclinedEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingPaidEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingPaymentFailedEvent;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;

import java.util.List;

@Slf4j
public class BookingDomainServiceImpl implements BookingDomainService {

  @Override
  public Booking validateAndInitiateBooking(Booking booking, Workshop workshop) {
    workshop.validateWorkshop();
    booking.validateBooking();
    booking.initBooking();
    log.info("New booking with id '{}' initialized", booking.getId().getValue().toString());
    return booking;
  }

  @Override
  public BookingApprovedEvent approveBooking(Booking booking, Workshop workshop) {
    workshop.validateWorkshop();
    booking.updateCurrentStatus(BookingStatus.APPROVED);
    log.info("Booking with id '{}' approved by workshop with id '{}'", booking.getIdValue(), workshop.getIdValue());
    return new BookingApprovedEvent(booking);
  }

  @Override
  public BookingDeclinedEvent declineBooking(Booking booking, Client client, List<String> failureMessages) {
    booking.updateCurrentStatusWithFailure(BookingStatus.DECLINED, failureMessages);
    log.info("Booking with id '{}' declined by client with id '{}'", booking.getIdValue(), client.getIdValue());
    return new BookingDeclinedEvent(booking);
  }

  @Override
  public BookingPaidEvent payBooking(Booking booking) {
    booking.updateCurrentStatus(BookingStatus.PAID);
    log.info("Booking with id '{}' paid successfully", booking.getIdValue());
    return new BookingPaidEvent(booking);
  }

  @Override
  public BookingPaymentFailedEvent cancelBookingPayment(Booking booking, List<String> failureMessages) {
    booking.updateCurrentStatusWithFailure(BookingStatus.PAYMENT_FAILED, failureMessages);
    log.info("Booking with id '{}' failed payment", booking.getIdValue());
    return new BookingPaymentFailedEvent(booking);
  }

  @Override
  public BookingCanceledEvent cancelBooking(Booking booking, List<String> failureMessages) {
    booking.updateCurrentStatusWithFailure(BookingStatus.CANCELLED, failureMessages);
    log.info("Booking with id '{}' cancelled", booking.getIdValue());
    return new BookingCanceledEvent(booking);
  }
}
