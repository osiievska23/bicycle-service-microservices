package org.vosiievska.bicycle.service.domain.core.event;

import org.vosiievska.bicycle.service.domain.core.entity.Booking;

public class BookingApprovedEvent extends BookingEvent {
  public BookingApprovedEvent(Booking booking) {
    super(booking);
  }
}
