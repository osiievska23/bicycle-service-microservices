package org.vosiievska.bicycle.service.event;

import org.vosiievska.bicycle.service.entity.Booking;

public class BookingPaidEvent extends BookingEvent {
  public BookingPaidEvent(Booking booking) {
    super(booking);
  }
}
