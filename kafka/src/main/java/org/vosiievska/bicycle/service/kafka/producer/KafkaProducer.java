package org.vosiievska.bicycle.service.kafka.producer;

import org.apache.avro.specific.SpecificRecordBase;

public interface KafkaProducer {

  void send(String topicName, String key, SpecificRecordBase message);

}
