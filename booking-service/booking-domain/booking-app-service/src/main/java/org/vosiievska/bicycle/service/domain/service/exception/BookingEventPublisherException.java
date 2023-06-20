package org.vosiievska.bicycle.service.domain.service.exception;

import org.vosiievska.bicycle.service.domain.exception.DomainException;

public class BookingEventPublisherException extends DomainException {

  public BookingEventPublisherException(String message, Object... args) {
    super(message, args);
  }
}
