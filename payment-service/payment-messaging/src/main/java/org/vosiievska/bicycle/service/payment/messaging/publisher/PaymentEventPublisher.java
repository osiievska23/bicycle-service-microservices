package org.vosiievska.bicycle.service.payment.messaging.publisher;

import com.bicycle.service.avro.payment.AvroPaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.vosiievska.bicycle.service.domain.event.DomainEvent;
import org.vosiievska.bicycle.service.domain.event.DomainEventPublisher;
import org.vosiievska.bicycle.service.kafka.producer.KafkaProducer;
import org.vosiievska.bicycle.service.payment.domain.event.PaymentCancelledEvent;
import org.vosiievska.bicycle.service.payment.domain.event.PaymentCompleteEvent;
import org.vosiievska.bicycle.service.payment.domain.event.PaymentEvent;
import org.vosiievska.bicycle.service.payment.domain.event.PaymentFailedEvent;
import org.vosiievska.bicycle.service.payment.domain.service.config.PaymentServiceConfigurationData;
import org.vosiievska.bicycle.service.payment.messaging.mapper.PaymentAvroMessagingMapper;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventPublisher<E extends PaymentEvent> implements DomainEventPublisher<E> {

  private final Set<Class<?>> SUPPORTED_PAYMENT_EVENT_CLASSES = Set.of(
      PaymentCancelledEvent.class, PaymentCompleteEvent.class, PaymentFailedEvent.class);

  private final KafkaProducer kafkaProducer;
  private final PaymentAvroMessagingMapper mapper;
  private final PaymentServiceConfigurationData configurationData;

  @Override
  public void publish(E event) {
    String eventClass = event.getClass().getSimpleName();
    String domainClass = event.getDomain().getClass().getSimpleName();
    String domainId = event.getDomain().getId().getValue().toString();

    log.info("Received '{}' for domain class '{}' by id: {}", eventClass, domainClass, domainId);

    String topicName = configurationData.getPaymentResponseTopicName();
    AvroPaymentResponse message = mapper.paymentEventToAvroPaymentResponse(event);

    kafkaProducer.send(topicName, domainId, message);
  }

  @Override
  public boolean supports(DomainEvent event) {
    return SUPPORTED_PAYMENT_EVENT_CLASSES.contains(event.getClass());
  }

}
