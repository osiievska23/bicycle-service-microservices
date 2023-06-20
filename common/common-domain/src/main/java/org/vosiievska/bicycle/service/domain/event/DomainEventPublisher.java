package org.vosiievska.bicycle.service.domain.event;

public interface DomainEventPublisher<E extends DomainEvent> {

  boolean supports(DomainEvent event);

  void publish(E event);
}
