package org.vosiievska.bicycle.service.payment.domain.exception;

import org.vosiievska.bicycle.service.domain.exception.EntityNotFoundException;

public class PaymentNotFoundException extends EntityNotFoundException {
  public PaymentNotFoundException(String message) {
    super(message);
  }

  public PaymentNotFoundException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public PaymentNotFoundException(String message, Object... args) {
    super(message, args);
  }
}
