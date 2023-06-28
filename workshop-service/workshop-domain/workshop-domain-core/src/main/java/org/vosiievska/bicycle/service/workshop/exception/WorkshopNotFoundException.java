package org.vosiievska.bicycle.service.workshop.exception;

import org.vosiievska.bicycle.service.domain.exception.EntityNotFoundException;

public class WorkshopNotFoundException extends EntityNotFoundException {
  public WorkshopNotFoundException(String message) {
    super(message);
  }

  public WorkshopNotFoundException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public WorkshopNotFoundException(String message, Object... args) {
    super(message, args);
  }
}
