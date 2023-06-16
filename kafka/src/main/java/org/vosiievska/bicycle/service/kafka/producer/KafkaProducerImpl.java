package org.vosiievska.bicycle.service.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.vosiievska.bicycle.service.kafka.exception.KafkaBrokerException;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducer {

  private final KafkaTemplate<String, String> kmKafkaTemplate;

  /**
   * The send API returns a CompletableFuture object. If we want to block the sending thread and get the result about
   * the sent message, we can call the get API of the CompletableFuture object. The thread will wait for the result,
   * but it will slow down the producer.
   * Kafka is a fast-stream processing platform. Therefore, it's better to handle the results asynchronously so that
   * the subsequent messages do not wait for the result of the previous message. (https://www.baeldung.com/spring-kafka)
   * We can do this through a callback:
   * @param topicName kafka topic name
   * @param key key
   * @param message message to send
   */
  @Override
  public void send(String topicName, String key, String message) {
    CompletableFuture<SendResult<String, String>> completableFuture = kmKafkaTemplate.send(topicName, key, message);
    completableFuture.whenComplete((res, ex) -> {
      if (ex == null) {
        long offset = res.getRecordMetadata().offset();
        log.info("Sent message=[{}] to the topic=[{}] with offset[{}]", message, topicName, offset);
      } else {
        log.info("Unable to send message=[{}] with exception message=[{}]", message, ex.getMessage());
        throw new KafkaBrokerException("Unable to send message=[%s] with exception message=[%s]", message, ex.getMessage());
      }
    });
  }
}
