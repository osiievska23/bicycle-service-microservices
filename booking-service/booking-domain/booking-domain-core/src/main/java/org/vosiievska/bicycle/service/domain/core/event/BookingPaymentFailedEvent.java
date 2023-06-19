package org.vosiievska.bicycle.service.domain.core.event;

import org.vosiievska.bicycle.service.domain.core.entity.Booking;

public class BookingPaymentFailedEvent extends BookingPaidEvent {
  public BookingPaymentFailedEvent(Booking booking) {
    super(booking);
  }
}
