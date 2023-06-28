package org.vosiievska.bicycle.service.workshop.exception;

import org.vosiievska.bicycle.service.domain.exception.DomainException;

public class WorkshopApplicationServiceException extends DomainException {

  public WorkshopApplicationServiceException(String message, Object... args) {
    super(message, args);
  }
}
