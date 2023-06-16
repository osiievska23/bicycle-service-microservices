package org.vosiievska.bicycle.service.exception;

import org.vosiievska.bicycle.service.domain.exception.DomainException;

public class BookingListenerException extends DomainException {

  public BookingListenerException(String message) {
    super(message);
  }

  public BookingListenerException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public BookingListenerException(String message, Object... args) {
    super(message, args);
  }
}
