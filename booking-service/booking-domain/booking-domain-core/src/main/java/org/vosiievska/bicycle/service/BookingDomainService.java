package org.vosiievska.bicycle.service;

import org.vosiievska.bicycle.service.entity.Booking;
import org.vosiievska.bicycle.service.entity.Client;
import org.vosiievska.bicycle.service.entity.Workshop;
import org.vosiievska.bicycle.service.event.BookingApprovedEvent;
import org.vosiievska.bicycle.service.event.BookingCanceledEvent;
import org.vosiievska.bicycle.service.event.BookingDeclinedEvent;
import org.vosiievska.bicycle.service.event.BookingPaidEvent;
import org.vosiievska.bicycle.service.event.BookingPaymentFailedEvent;

import java.util.List;

public interface BookingDomainService {

  Booking validateAndInitiateBooking(Booking booking, Workshop workshop);

  BookingApprovedEvent approveBooking(Booking booking, Workshop workshop);

  BookingDeclinedEvent declineBooking(Booking booking, Client client, List<String> failureMessages);

  BookingPaidEvent payBooking(Booking booking);

  BookingPaymentFailedEvent cancelBookingPayment(Booking booking, List<String> failureMessages);

  BookingCanceledEvent cancelBooking(Booking booking, List<String> failureMessages);
}
