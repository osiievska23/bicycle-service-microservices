package org.vosiievska.bicycle.service.domain.core.event;

import org.vosiievska.bicycle.service.domain.core.entity.Booking;

public class BookingPaidEvent extends BookingEvent {
  public BookingPaidEvent(Booking booking) {
    super(booking);
  }
}
