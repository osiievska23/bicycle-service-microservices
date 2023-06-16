package org.vosiievska.bicycle.service.publisher;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Component;
import org.vosiievska.bicycle.service.config.BookingServiceConfigurationData;
import org.vosiievska.bicycle.service.event.BookingCreatedEvent;
import org.vosiievska.bicycle.service.kafka.producer.KafkaProducer;
import org.vosiievska.bicycle.service.mapper.BookingKafkaMessagingMapper;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingCreatedEventPublisher extends AbstractDomainEventPublisher<BookingCreatedEvent> {

  BookingKafkaMessagingMapper messagingMapper;
  BookingServiceConfigurationData configurationData;

  public BookingCreatedEventPublisher(KafkaProducer kafkaProducer,
                                        BookingKafkaMessagingMapper messagingMapper,
                                        BookingServiceConfigurationData configurationData) {
    super(kafkaProducer);
    this.messagingMapper = messagingMapper;
    this.configurationData = configurationData;
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
