package org.vosiievska.bicycle.service.event;

import org.vosiievska.bicycle.service.entity.Booking;

public class BookingCanceledEvent extends BookingEvent {
  public BookingCanceledEvent(Booking booking) {
    super(booking);
  }
}
