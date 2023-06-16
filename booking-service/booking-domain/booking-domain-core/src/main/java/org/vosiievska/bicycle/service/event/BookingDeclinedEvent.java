package org.vosiievska.bicycle.service.event;

import org.vosiievska.bicycle.service.entity.Booking;

public class BookingDeclinedEvent extends BookingEvent {
  public BookingDeclinedEvent(Booking booking) {
    super(booking);
  }
}
