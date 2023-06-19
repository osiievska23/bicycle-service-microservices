package org.vosiievska.bicycle.service.messaging.publisher;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Component;
import org.vosiievska.bicycle.service.domain.core.event.BookingCanceledEvent;
import org.vosiievska.bicycle.service.domain.service.config.BookingServiceConfigurationData;
import org.vosiievska.bicycle.service.kafka.producer.KafkaProducer;
import org.vosiievska.bicycle.service.messaging.mapper.BookingKafkaMessagingMapper;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingCancelledEventPublisher extends AbstractDomainEventPublisher<BookingCanceledEvent> {

  BookingKafkaMessagingMapper messagingMapper;
  BookingServiceConfigurationData configurationData;

  public BookingCancelledEventPublisher(KafkaProducer kafkaProducer,
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
  SpecificRecordBase getRequestMessage(BookingCanceledEvent event) {
    return messagingMapper.bookingEventToAvroPaymentRequest(event);
  }
}
