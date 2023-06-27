package org.vosiievska.bicycle.service.domain.core.event;

import org.vosiievska.bicycle.service.domain.core.entity.Booking;
import org.vosiievska.bicycle.service.domain.event.AbstractDomainEvent;

import java.util.List;

public abstract class BookingEvent extends AbstractDomainEvent<Booking> {

  public BookingEvent(Booking booking, List<String> failureMessages) {
    super(booking, failureMessages);
  }

  public BookingEvent(Booking booking) {
    super(booking);
  }

  public Booking getBooking() {
    return super.getDomain();
  }
}
