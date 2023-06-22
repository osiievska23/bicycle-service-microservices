package org.vosiievska.bicycle.service.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.vosiievska.bicycle.service.domain.exception.DomainException;
import org.vosiievska.bicycle.service.domain.exception.EntityNotFoundException;

import java.time.Instant;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(DomainException.class)
  public final ErrorDetails handleDomainException(DomainException ex, WebRequest request) {
    return buildErrorDetailsResponse(ex, request);
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(EntityNotFoundException.class)
  public final ErrorDetails handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
    return buildErrorDetailsResponse(ex, request);
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public final ErrorDetails handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                  WebRequest request) {
    log.error("MethodArgumentNotValidException: {}", ex.getMessage());
    String fieldErrors = getFieldErrorsMessageFromBindingResult(ex.getBindingResult());
    String message = String.format("Validation errors count: %s. Field errors: [%s]", ex.getErrorCount(), fieldErrors);
    return new ErrorDetails(message, request.getDescription(false));
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public final ErrorDetails handleIllegalArgumentException(Exception ex, WebRequest request) {
    log.warn("Unexpected error occurred!");
    return new ErrorDetails(ex.getClass().getName() + ": " + ex.getMessage(), request.getDescription(false));
  }

  private ErrorDetails buildErrorDetailsResponse(Exception ex, WebRequest request) {
    log.warn("{}: {}", ex.getClass().getSimpleName(), ex.getMessage());
    return new ErrorDetails(ex.getMessage(), request.getDescription(false));
  }

  private String getFieldErrorsMessageFromBindingResult(BindingResult bindingResult) {
    return bindingResult.getFieldErrors().stream()
        .map(dm -> dm.getField() + ": " + dm.getDefaultMessage())
        .collect(Collectors.joining("; "));
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class ErrorDetails {

    private Instant timestamp;
    private String message;
    private String details;

    ErrorDetails(String message, String details) {
      this.timestamp = Instant.now();
      this.message = message;
      this.details = details;
    }
  }
}
