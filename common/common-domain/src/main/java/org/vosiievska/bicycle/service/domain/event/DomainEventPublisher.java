package org.vosiievska.bicycle.service.domain.event;

public interface DomainEventPublisher {
  void publish(DomainEvent event);
}
