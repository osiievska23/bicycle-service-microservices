package org.vosiievska.bicycle.service.payment.domain.exception;

import org.vosiievska.bicycle.service.domain.exception.DomainException;

public class PaymentDomainException extends DomainException {
  public PaymentDomainException(String message) {
    super(message);
  }

  public PaymentDomainException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public PaymentDomainException(String message, Object... args) {
    super(message, args);
  }
}
