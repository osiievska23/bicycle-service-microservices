package org.vosiievska.bicycle.service.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
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

  private final KafkaTemplate<String, SpecificRecordBase> kmKafkaTemplate;

  /**
   * The send API returns a CompletableFuture object. If we want to block the sending thread and get the result about
   * the send message, we can call the get API of the CompletableFuture object. The thread will wait for the result,
   * but it will slow down the producer.
   * Kafka is a fast-stream processing platform. Therefore, it's better to handle the results asynchronously so that
   * the subsequent messages do not wait for the result of the previous message.
   * We can do this through a callback as shown below.
   * See: <a href="https://www.baeldung.com/spring-kafka#2-publishing-messages">Spring kafka publishing messages</a>
   * @param topicName kafka topic name
   * @param key key
   * @param message message to send
   */
  @Override
  public void send(String topicName, String key, SpecificRecordBase message) {
    var completableFuture = kmKafkaTemplate.send(topicName, key, message);
    completableFuture.whenComplete((res, ex) -> {
      if (ex == null) {
        long offset = res.getRecordMetadata().offset();
        log.info("Sent message=[{}] by key [{}] to the topic=[{}] with offset[{}]", message, key, topicName, offset);
      } else {
        log.info("Unable to send message=[{}] by key [{}] with exception message=[{}]", message, key, ex.getMessage());
        throw new KafkaBrokerException(
                "Unable to send message=[%s] by key [%s] with exception message=[{}]", message, key, ex.getMessage());
      }
    });
  }
}
