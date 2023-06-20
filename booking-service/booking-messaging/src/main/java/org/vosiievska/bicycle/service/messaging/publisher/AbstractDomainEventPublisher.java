package org.vosiievska.bicycle.service.messaging.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.vosiievska.bicycle.service.domain.event.AbstractDomainEvent;
import org.vosiievska.bicycle.service.domain.event.DomainEvent;
import org.vosiievska.bicycle.service.domain.event.DomainEventPublisher;
import org.vosiievska.bicycle.service.kafka.producer.KafkaProducer;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractDomainEventPublisher<E extends AbstractDomainEvent<?>> implements DomainEventPublisher<E> {

  private final KafkaProducer kafkaProducer;

  @Override
  public void publish(E event) {
    String eventClass = event.getClass().getSimpleName();
    String domainClass = event.getDomain().getClass().getSimpleName();
    String domainId = event.getDomain().getId().getValue().toString();

    log.info("Received {} for domain class: {} by id: {}", eventClass, domainClass, domainId);

    kafkaProducer.send(getTopicName(), domainId, getRequestMessage(event));
  }

  @Override
  public abstract boolean supports(DomainEvent event);

  abstract String getTopicName();

  abstract SpecificRecordBase getRequestMessage(E event);
}
