package org.vosiievska.bicycle.service.domain.core.event;

import org.vosiievska.bicycle.service.domain.core.entity.Booking;

public class BookingCanceledEvent extends BookingEvent {
  public BookingCanceledEvent(Booking booking) {
    super(booking);
  }
}
