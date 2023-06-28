package org.vosiievska.bicycle.service.workshop.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "workshop-service")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkshopServiceConfigurationData {

  String workshopApprovalRequestTopicName;
  String workshopApprovalResponseTopicName;

}
