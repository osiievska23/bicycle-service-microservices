package org.vosiievska.bicycle.service.workshop.messaging.exception;

import org.vosiievska.bicycle.service.domain.exception.DomainException;

public class WorkshopListenerException extends DomainException {

  public WorkshopListenerException(String message) {
    super(message);
  }
}
