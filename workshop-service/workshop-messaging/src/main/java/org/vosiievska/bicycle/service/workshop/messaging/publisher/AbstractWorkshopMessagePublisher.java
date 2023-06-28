package org.vosiievska.bicycle.service.workshop.messaging.publisher;

import com.bicycle.service.avro.payment.AvroWorkshopApprovalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.vosiievska.bicycle.service.domain.event.DomainEvent;
import org.vosiievska.bicycle.service.domain.event.DomainEventPublisher;
import org.vosiievska.bicycle.service.kafka.producer.KafkaProducer;
import org.vosiievska.bicycle.service.workshop.config.WorkshopServiceConfigurationData;
import org.vosiievska.bicycle.service.workshop.event.BookingApprovedEvent;
import org.vosiievska.bicycle.service.workshop.event.BookingDeclinedEvent;
import org.vosiievska.bicycle.service.workshop.event.WorkshopApprovalResponseEvent;
import org.vosiievska.bicycle.service.workshop.messaging.mapper.WorkshopAvroMessagingMapper;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractWorkshopMessagePublisher<E extends WorkshopApprovalResponseEvent> implements DomainEventPublisher<E> {

  private final Set<Class<?>> SUPPORTED_WORKSHOP_EVENT_CLASSES = Set.of(
      BookingApprovedEvent.class, BookingDeclinedEvent.class);

  private final KafkaProducer kafkaProducer;
  private final WorkshopAvroMessagingMapper mapper;
  private final WorkshopServiceConfigurationData configurationData;

  @Override
  public void publish(E event) {
    String eventClass = event.getClass().getSimpleName();
    String domainClass = event.getDomain().getClass().getSimpleName();
    String domainId = event.getDomain().getId().getValue().toString();

    log.info("Received '{}' for domain class '{}' by id: {}", eventClass, domainClass, domainId);

    String topicName = configurationData.getWorkshopApprovalResponseTopicName();
    AvroWorkshopApprovalResponse message = mapper.workshopApprovalEventToAvroWorkshopResponse(event);

    kafkaProducer.send(topicName, domainId, message);
  }

  @Override
  public boolean supports(DomainEvent event) {
    return SUPPORTED_WORKSHOP_EVENT_CLASSES.contains(event.getClass());
  }

}
