package org.vosiievska.bicycle.service.payment.domain.service.listener;

import org.vosiievska.bicycle.service.payment.domain.service.dto.PaymentRequest;

/**
 * Primary port according to the Hexagonal Architecture
 */
public interface PaymentRequestMessageListener {

  void completePayment(PaymentRequest paymentRequest);

  void cancelPayment(PaymentRequest paymentRequest);
}
