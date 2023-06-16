package org.vosiievska.bicycle.service.kafka.producer;

public interface KafkaProducer {

  void send(String topicName, String key, String message);

}
