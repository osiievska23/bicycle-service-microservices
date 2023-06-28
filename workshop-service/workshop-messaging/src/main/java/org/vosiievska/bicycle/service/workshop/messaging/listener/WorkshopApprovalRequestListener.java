package org.vosiievska.bicycle.service.workshop.messaging.listener;

import com.bicycle.service.avro.payment.AvroWorkshopApprovalRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.vosiievska.bicycle.service.kafka.consumer.KafkaConsumer;
import org.vosiievska.bicycle.service.workshop.listener.WorkshopApprovalRequestMessageListener;
import org.vosiievska.bicycle.service.workshop.messaging.mapper.WorkshopAvroMessagingMapper;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WorkshopApprovalRequestListener implements KafkaConsumer<String, AvroWorkshopApprovalRequest> {

  private final WorkshopAvroMessagingMapper messagingMapper;
  private final WorkshopApprovalRequestMessageListener approvalRequestMessageListener;

  @Override
  @KafkaListener(
      id = "${kafka-consumer-config.workshop-approval-request-group-id}",
      topics = "${workshop-service.workshop-approval-request-topic-name}")
  public void listen(@Payload List<AvroWorkshopApprovalRequest> messages,
                     @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                     @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                     @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
    log.info("Received {} workshop approval request messages with keys: {}, partitions: {} and offsets: {}",
        messages.size(), keys.toString(), partitions.toString(), offsets.toString());

    messagingMapper.avroWorkshopApprovalRequestToRequestList(messages)
        .forEach(approvalRequestMessageListener::approveBooking);
  }
}
