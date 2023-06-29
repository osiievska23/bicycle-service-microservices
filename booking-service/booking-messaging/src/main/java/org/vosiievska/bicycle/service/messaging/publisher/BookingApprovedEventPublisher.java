package org.vosiievska.bicycle.service.messaging.publisher;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Component;
import org.vosiievska.bicycle.service.domain.core.event.BookingApprovedEvent;
import org.vosiievska.bicycle.service.domain.event.DomainEvent;
import org.vosiievska.bicycle.service.domain.service.config.BookingServiceConfigurationData;
import org.vosiievska.bicycle.service.kafka.producer.KafkaProducer;
import org.vosiievska.bicycle.service.messaging.mapper.BookingAvroMessagingMapper;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingApprovedEventPublisher extends AbstractBookingEventPublisher<BookingApprovedEvent> {

  BookingAvroMessagingMapper messagingMapper;
  BookingServiceConfigurationData configurationData;

  public BookingApprovedEventPublisher(KafkaProducer kafkaProducer, BookingAvroMessagingMapper messagingMapper,
                                       BookingServiceConfigurationData configurationData) {
    super(kafkaProducer);
    this.messagingMapper = messagingMapper;
    this.configurationData = configurationData;
  }

  @Override
  public boolean supports(DomainEvent event) {
    return BookingApprovedEvent.class.equals(event.getClass());
  }

  @Override
  public String getTopicName() {
    return configurationData.getPaymentRequestTopicName();
  }

  @Override
  public SpecificRecordBase getRequestMessage(BookingApprovedEvent event) {
    return messagingMapper.bookingEventToAvroPaymentRequest(event);
  }
}
