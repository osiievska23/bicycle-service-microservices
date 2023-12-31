package org.vosiievska.bicycle.service.payment.domain.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vosiievska.bicycle.service.payment.domain.PaymentDomainService;
import org.vosiievska.bicycle.service.payment.domain.entity.Payment;
import org.vosiievska.bicycle.service.payment.domain.event.PaymentCompleteEvent;
import org.vosiievska.bicycle.service.payment.domain.event.PaymentEvent;
import org.vosiievska.bicycle.service.payment.domain.event.PaymentFailedEvent;
import org.vosiievska.bicycle.service.payment.domain.service.dto.PaymentRequest;
import org.vosiievska.bicycle.service.payment.domain.service.mapper.PaymentMapper;
import org.vosiievska.bicycle.service.payment.domain.service.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PaymentApplicationServiceImpl implements PaymentApplicationService {

  private final PaymentMapper paymentMapper;
  private final PaymentDomainService paymentDomainService;
  private final PaymentRepository paymentRepository;

  @Override
  public PaymentEvent createCompletedPayment(PaymentRequest paymentRequest) {
    log.info("Received payment complete event for booking id; {}", paymentRequest.getBookingId());
    Payment payment = paymentMapper.paymentRequestToPaymentEntity(paymentRequest);
    List<String> failureMessages = new ArrayList<>();
    Payment validatedPayment = paymentDomainService.validateAndInitiatePayment(payment, failureMessages);
    Payment saved = paymentRepository.save(validatedPayment);
    return failureMessages.isEmpty() ? buildSuccessPayment(saved) : buildFailedPayment(saved, failureMessages);
  }

  private PaymentCompleteEvent buildSuccessPayment(Payment payment) {
    log.info("Payment successfully initialized for booking by id: {}", payment.getBookingId().getValue());
    return new PaymentCompleteEvent(payment);
  }

  private PaymentFailedEvent buildFailedPayment(Payment payment, List<String> failureMessages) {
    log.info("Payment failed for booking by id: {}", payment.getBookingId().getValue());
    return new PaymentFailedEvent(payment, failureMessages);
  }
}
