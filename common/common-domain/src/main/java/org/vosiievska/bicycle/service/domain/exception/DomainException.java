package org.vosiievska.bicycle.service.domain.exception;

public class DomainException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DomainException(String message) {
    super(message);
  }

  public DomainException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public DomainException(String message, Object... args) {
    super(String.format(message, args));
  }

}
