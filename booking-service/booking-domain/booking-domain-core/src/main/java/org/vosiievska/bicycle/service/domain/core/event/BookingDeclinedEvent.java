package org.vosiievska.bicycle.service.domain.core.event;

import org.vosiievska.bicycle.service.domain.core.entity.Booking;

public class BookingDeclinedEvent extends BookingEvent {
  public BookingDeclinedEvent(Booking booking) {
    super(booking);
  }
}
