package org.vosiievska.bicycle.service.payment.dataaccess.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.vosiievska.bicycle.service.dataaccess.jpa.entity.PaymentEntity;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.payment.domain.entity.Payment;
import org.vosiievska.bicycle.service.payment.domain.service.repository.PaymentRepository;
import org.vosiievska.bicycle.service.payment.dataaccess.mapper.PaymentJpaEntityMapper;
import org.vosiievska.bicycle.service.payment.dataaccess.repository.PaymentJpaRepository;

import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

  private final PaymentJpaRepository jpaRepository;
  private final PaymentJpaEntityMapper mapper;

  @Override
  public Payment save(Payment payment) {
    PaymentEntity savedEntity = jpaRepository.save(mapper.paymentToJpaEntity(payment));
    return mapper.jpaEntityToPayment(savedEntity);
  }

  @Override
  public Optional<Payment> findByBookingId(BookingId bookingId) {
    return jpaRepository.findByBookingId(bookingId.getValue())
        .map(mapper::jpaEntityToPayment);
  }
}
