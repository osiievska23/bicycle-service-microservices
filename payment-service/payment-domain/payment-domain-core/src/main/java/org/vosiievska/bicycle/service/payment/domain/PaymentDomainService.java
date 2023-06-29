package org.vosiievska.bicycle.service.payment.domain;

import org.vosiievska.bicycle.service.payment.domain.entity.Payment;

import java.util.List;

public interface PaymentDomainService {

  Payment validateAndInitiatePayment(Payment payment, List<String> failureMessages);

}
