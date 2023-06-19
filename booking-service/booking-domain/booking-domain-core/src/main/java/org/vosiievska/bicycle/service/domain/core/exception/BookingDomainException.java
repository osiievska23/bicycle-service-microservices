package org.vosiievska.bicycle.service.domain.core.exception;

import org.vosiievska.bicycle.service.domain.exception.DomainException;

public class BookingDomainException extends DomainException {

  public BookingDomainException(String message) {
    super(message);
  }

  public BookingDomainException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public BookingDomainException(String message, Object... args) {
    super(message, args);
  }
}
