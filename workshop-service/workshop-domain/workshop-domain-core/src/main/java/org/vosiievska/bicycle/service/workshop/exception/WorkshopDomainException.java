package org.vosiievska.bicycle.service.workshop.exception;

import org.vosiievska.bicycle.service.domain.exception.DomainException;

public class WorkshopDomainException extends DomainException {
  public WorkshopDomainException(String message) {
    super(message);
  }

  public WorkshopDomainException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public WorkshopDomainException(String message, Object... args) {
    super(message, args);
  }
}
