package org.vosiievska.bicycle.service.domain.event;

import lombok.Getter;
import org.vosiievska.bicycle.service.domain.entity.BaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A Domain Event should be represented explicitly.
 * Domain events are used to notify other services when something happens. This service implementations would represent
 * domain events related to a specific entity.
 * @param <T> domain class
 */
@Getter
public abstract class AbstractDomainEvent<T extends BaseEntity<?>> implements DomainEvent {

  private final T domain;
  private final LocalDateTime createdAt;
  private final List<String> failureMessages;

  public AbstractDomainEvent(T domain, LocalDateTime createdAt, List<String> failureMessages) {
    this.domain = domain;
    this.createdAt = createdAt;
    this.failureMessages = failureMessages;
  }

  public AbstractDomainEvent(T domain, List<String> failureMessages) {
    this(domain, LocalDateTime.now(), failureMessages);
  }

  public AbstractDomainEvent(T domain) {
    this(domain, LocalDateTime.now(), new ArrayList<>());
  }

}
