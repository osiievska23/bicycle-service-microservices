package org.vosiievska.bicycle.service.messaging.listener;

import com.bicycle.service.avro.payment.AvroPaymentResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.vosiievska.bicycle.service.domain.service.dto.response.PaymentResponse;
import org.vosiievska.bicycle.service.domain.service.listener.PaymentResponseListener;
import org.vosiievska.bicycle.service.kafka.consumer.KafkaConsumer;
import org.vosiievska.bicycle.service.messaging.exception.BookingListenerException;
import org.vosiievska.bicycle.service.messaging.mapper.BookingAvroMessagingMapper;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentResponseKafkaListener implements KafkaConsumer<String, AvroPaymentResponse> {

  BookingAvroMessagingMapper messagingMapper;
  PaymentResponseListener paymentResponseListener;

  @Override
  @KafkaListener(
      id = "${kafka-consumer-config.payment-response-group-id}",
      topics = "${booking-service.payment-response-topic-name}")
  public void listen(@Payload List<AvroPaymentResponse> messages,
                     @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                     @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                     @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

    log.info("Received {} booking payment response messages with keys: {}, partitions: {} and offsets: {}",
        messages.size(), keys.toString(), partitions.toString(), offsets.toString());

    messagingMapper.avroPaymentResponseToPaymentRequestList(messages).forEach(this::proceedPaymentResponse);
  }

  private void proceedPaymentResponse(PaymentResponse paymentResponse) {
    switch (paymentResponse.getPaymentStatus()) {
      case COMPLETED -> paymentResponseListener.paidSuccessfully(paymentResponse);
      case CANCELLED -> paymentResponseListener.paymentCancelled(paymentResponse);
      case FAILED -> paymentResponseListener.paymentFailed(paymentResponse);
      default -> throw new BookingListenerException("Invalid payment response status");
    }
  }
}
