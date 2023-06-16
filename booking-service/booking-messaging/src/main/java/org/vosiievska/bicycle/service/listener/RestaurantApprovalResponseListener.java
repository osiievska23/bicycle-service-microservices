package org.vosiievska.bicycle.service.listener;

import com.bicycle.service.avro.payment.AvroWorkshopApprovalResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.vosiievska.bicycle.service.dto.response.WorkshopResponse;
import org.vosiievska.bicycle.service.exception.BookingListenerException;
import org.vosiievska.bicycle.service.kafka.consumer.KafkaConsumer;
import org.vosiievska.bicycle.service.mapper.BookingKafkaMessagingMapper;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestaurantApprovalResponseListener implements KafkaConsumer<String, AvroWorkshopApprovalResponse> {

  BookingKafkaMessagingMapper messagingMapper;
  WorkshopResponseListener workshopResponseListener;

  @Override
  @KafkaListener(
      id = "${kafka-consumer-config.workshop-approval-group-id}",
      topics = "${booking-service.workshop-approval-topic-name}")
  public void listen(@Payload List<AvroWorkshopApprovalResponse> messages,
                     @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                     @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                     @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

    log.info("Received {} booking payment response messages with keys: {}, partitions: {} and offsets: {}",
        messages.size(), keys.toString(), partitions.toString(), offsets.toString());

    messagingMapper.avroWorkshopResponseToPaymentRequestList(messages).forEach(this::proceedPaymentResponse);
  }

  private void proceedPaymentResponse(WorkshopResponse workshopResponse) {
    switch (workshopResponse.getWorkshopResponseStatus()) {
      case APPROVED -> workshopResponseListener.bookingApproved(workshopResponse);
      case DECLINED -> workshopResponseListener.bookingDeclined(workshopResponse);
      default -> throw new BookingListenerException("Invalid payment response status");
    }
  }
}
