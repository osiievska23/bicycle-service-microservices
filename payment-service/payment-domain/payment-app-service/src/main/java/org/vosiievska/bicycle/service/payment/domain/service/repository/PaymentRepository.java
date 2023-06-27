package org.vosiievska.bicycle.service.payment.domain.service.repository;

import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.payment.domain.entity.Payment;

import java.util.Optional;

/**
 * Secondary port according to the Hexagonal Architecture
 */
public interface PaymentRepository {

  Payment save(Payment payment);

  Optional<Payment> findByBookingId(BookingId bookingId);
}
