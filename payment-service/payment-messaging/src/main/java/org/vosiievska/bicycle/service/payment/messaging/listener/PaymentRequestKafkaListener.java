package org.vosiievska.bicycle.service.payment.messaging.listener;

import com.bicycle.service.avro.payment.AvroPaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.vosiievska.bicycle.service.kafka.consumer.KafkaConsumer;
import org.vosiievska.bicycle.service.payment.domain.service.listener.PaymentRequestMessageListener;
import org.vosiievska.bicycle.service.payment.messaging.mapper.PaymentAvroMessagingMapper;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentRequestKafkaListener implements KafkaConsumer<String, AvroPaymentRequest> {

  private final PaymentAvroMessagingMapper messagingMapper;
  private final PaymentRequestMessageListener paymentRequestMessageListener;

  @Override
  @KafkaListener(
      id = "${kafka-consumer-config.payment-consumer-group-id}",
      topics = "${payment-service.payment-request-topic-name}")
  public void listen(@Payload List<AvroPaymentRequest> messages,
                     @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                     @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                     @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
    log.info("Received {} payment request messages with keys: {}, partitions: {} and offsets: {}",
        messages.size(), keys.toString(), partitions.toString(), offsets.toString());

    messagingMapper.avroPaymentRequestToPaymentRequest(messages)
        .forEach(paymentRequestMessageListener::completePayment);
  }
}
