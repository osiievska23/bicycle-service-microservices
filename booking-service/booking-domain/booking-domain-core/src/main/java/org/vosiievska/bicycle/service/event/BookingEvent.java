package org.vosiievska.bicycle.service.event;

import org.vosiievska.bicycle.service.domain.event.AbstractDomainEvent;
import org.vosiievska.bicycle.service.entity.Booking;

import java.time.Instant;

public abstract class BookingEvent extends AbstractDomainEvent<Booking> {

  public BookingEvent(Booking booking, Instant createdAt) {
    super(booking, createdAt);
  }

  public BookingEvent(Booking booking) {
    super(booking);
  }
  public Booking getBooking() {
    return super.getDomain();
  }
}
