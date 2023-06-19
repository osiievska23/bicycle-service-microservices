package org.vosiievska.bicycle.service.domain.service.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "booking-service")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingServiceConfigurationData {

  String paymentRequestTopicName;
  String paymentResponseTopicName;
  String workshopApprovalRequestTopicName;
  String workshopApprovalResponseTopicName;

}
