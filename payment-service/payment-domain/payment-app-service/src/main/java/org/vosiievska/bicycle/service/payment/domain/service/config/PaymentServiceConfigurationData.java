package org.vosiievska.bicycle.service.payment.domain.service.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "payment-service")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentServiceConfigurationData {

  String paymentRequestTopicName;
  String paymentResponseTopicName;

}
