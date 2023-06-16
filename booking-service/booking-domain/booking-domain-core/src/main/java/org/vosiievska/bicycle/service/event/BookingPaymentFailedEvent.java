package org.vosiievska.bicycle.service.event;

import org.vosiievska.bicycle.service.entity.Booking;

public class BookingPaymentFailedEvent extends BookingPaidEvent {
  public BookingPaymentFailedEvent(Booking booking) {
    super(booking);
  }
}
