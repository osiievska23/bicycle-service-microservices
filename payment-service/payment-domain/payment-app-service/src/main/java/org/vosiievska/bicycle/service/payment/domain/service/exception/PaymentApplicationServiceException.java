package org.vosiievska.bicycle.service.payment.domain.service.exception;

import org.vosiievska.bicycle.service.domain.exception.DomainException;

public class PaymentApplicationServiceException extends DomainException {

  public PaymentApplicationServiceException(String message, Object... args) {
    super(message, args);
  }
}
