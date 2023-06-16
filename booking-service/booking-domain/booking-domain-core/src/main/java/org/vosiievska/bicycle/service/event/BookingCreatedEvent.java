package org.vosiievska.bicycle.service.event;

import org.vosiievska.bicycle.service.entity.Booking;

public class BookingCreatedEvent extends BookingEvent {
  public BookingCreatedEvent(Booking booking) {
    super(booking);
  }
}
