package org.vosiievska.bicycle.service.domain.event;

import lombok.Getter;
import org.vosiievska.bicycle.service.domain.entity.Entity;

import java.time.LocalDateTime;

/**
 * A Domain Event should be represented explicitly.
 * Domain events are used to notify other services when something happens. This service implementations would represent
 * domain events related to a specific entity.
 * @param <T> domain class
 */
@Getter
public abstract class AbstractDomainEvent<T extends Entity<?>> implements DomainEvent {

  private final T domain;
  private final LocalDateTime createdAt;

  public AbstractDomainEvent(T booking, LocalDateTime createdAt) {
    this.domain = booking;
    this.createdAt = createdAt;
  }

  public AbstractDomainEvent(T booking) {
    this(booking, LocalDateTime.now());
  }

}
