package org.vosiievska.bicycle.service.payment.domain.service.service;

import org.vosiievska.bicycle.service.payment.domain.event.PaymentEvent;
import org.vosiievska.bicycle.service.payment.domain.service.dto.PaymentRequest;

public interface PaymentApplicationService {

  PaymentEvent createCompletedPayment(PaymentRequest paymentRequest);

  PaymentEvent createCancelPayment(PaymentRequest paymentRequest);

}
