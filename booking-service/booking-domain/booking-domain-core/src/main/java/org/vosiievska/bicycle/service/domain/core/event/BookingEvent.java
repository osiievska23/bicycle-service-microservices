package org.vosiievska.bicycle.service.domain.core.event;

import org.vosiievska.bicycle.service.domain.core.entity.Booking;
import org.vosiievska.bicycle.service.domain.event.AbstractDomainEvent;

import java.time.LocalDateTime;

public abstract class BookingEvent extends AbstractDomainEvent<Booking> {

  public BookingEvent(Booking booking, LocalDateTime createdAt) {
    super(booking, createdAt);
  }

  public BookingEvent(Booking booking) {
    super(booking);
  }
  public Booking getBooking() {
    return super.getDomain();
  }
}
