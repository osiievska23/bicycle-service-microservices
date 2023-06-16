package org.vosiievska.bicycle.service.domain.event;

public interface DomainEventPublisher<E extends DomainEvent> {

  void publish(E event);
}
