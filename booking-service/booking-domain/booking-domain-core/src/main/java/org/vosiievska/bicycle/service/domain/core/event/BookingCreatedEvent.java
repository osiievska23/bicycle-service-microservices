package org.vosiievska.bicycle.service.domain.core.event;

import org.vosiievska.bicycle.service.domain.core.entity.Booking;

public class BookingCreatedEvent extends BookingEvent {
  public BookingCreatedEvent(Booking booking) {
    super(booking);
  }
}
