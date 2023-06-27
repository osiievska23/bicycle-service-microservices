package org.vosiievska.payment.service.messaging.exception;

import org.vosiievska.bicycle.service.domain.exception.DomainException;

public class PaymentListenerException extends DomainException {

  public PaymentListenerException(String message) {
    super(message);
  }
}
