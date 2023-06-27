package org.vosiievska.bicycle.service.messaging.exception;

import org.vosiievska.bicycle.service.domain.exception.DomainException;

public class BookingListenerException extends DomainException {

  public BookingListenerException(String message) {
    super(message);
  }
}
