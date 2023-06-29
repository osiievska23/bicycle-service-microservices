package org.vosiievska.bicycle.service.domain.core;

import org.vosiievska.bicycle.service.domain.core.entity.Booking;
import org.vosiievska.bicycle.service.domain.core.entity.Client;
import org.vosiievska.bicycle.service.domain.core.entity.Workshop;
import org.vosiievska.bicycle.service.domain.core.event.BookingApprovedEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingCanceledEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingDeclinedEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingPaidEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingPaymentFailedEvent;

import java.util.List;

public interface BookingDomainService {

  Booking validateAndInitiateBooking(Booking booking);

  BookingApprovedEvent approveBooking(Booking booking, Workshop workshop);

  BookingDeclinedEvent declineBooking(Booking booking, Client client, List<String> failureMessages);

  BookingPaidEvent payBooking(Booking booking);

  BookingPaymentFailedEvent cancelBookingPayment(Booking booking, List<String> failureMessages);

  BookingCanceledEvent cancelBooking(Booking booking, List<String> failureMessages);
}
