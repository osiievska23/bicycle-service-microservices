package org.vosiievska.bicycle.service.messaging.publisher;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Component;
import org.vosiievska.bicycle.service.domain.core.event.BookingCreatedEvent;
import org.vosiievska.bicycle.service.domain.event.DomainEvent;
import org.vosiievska.bicycle.service.domain.service.config.BookingServiceConfigurationData;
import org.vosiievska.bicycle.service.kafka.producer.KafkaProducer;
import org.vosiievska.bicycle.service.messaging.mapper.BookingAvroMessagingMapper;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingCreatedEventPublisher extends AbstractDomainEventPublisher<BookingCreatedEvent> {

  BookingAvroMessagingMapper messagingMapper;
  BookingServiceConfigurationData configurationData;

  public BookingCreatedEventPublisher(KafkaProducer kafkaProducer,
                                        BookingAvroMessagingMapper messagingMapper,
                                        BookingServiceConfigurationData configurationData) {
    super(kafkaProducer);
    this.messagingMapper = messagingMapper;
    this.configurationData = configurationData;
  }

  @Override
  public boolean supports(DomainEvent event) {
    return event.getClass().equals(BookingCreatedEvent.class);
  }

  @Override
  String getTopicName() {
    return configurationData.getPaymentRequestTopicName();
  }

  @Override
  SpecificRecordBase getRequestMessage(BookingCreatedEvent event) {
    return messagingMapper.bookingEventToAvroPaymentRequest(event);
  }
}
