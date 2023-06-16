package org.vosiievska.bicycle.service.event;

import org.vosiievska.bicycle.service.entity.Booking;

public class BookingApprovedEvent extends BookingEvent {
  public BookingApprovedEvent(Booking booking) {
    super(booking);
  }
}
