package org.vosiievska.bicycle.service.payment.domain;

import lombok.extern.slf4j.Slf4j;
import org.vosiievska.bicycle.service.domain.valueobject.PaymentStatus;
import org.vosiievska.bicycle.service.payment.domain.entity.Payment;

import java.util.List;

public class PaymentDomainServiceImpl implements PaymentDomainService {

  @Override
  public Payment validateAndInitiatePayment(Payment payment, List<String> failureMessages) {
    payment.validatePaymentPrice(failureMessages);
    payment.initPayment();
    payment.updateCurrentStatus(failureMessages.isEmpty() ? PaymentStatus.COMPLETED : PaymentStatus.FAILED);
    return payment;
  }

  @Override
  public Payment validateAndCancelPayment(Payment payment, List<String> failureMessages) {
    payment.validatePaymentPrice(failureMessages);
    payment.updateCurrentStatus(failureMessages.isEmpty() ? PaymentStatus.CANCELLED : PaymentStatus.FAILED);
    return payment;
  }
}
