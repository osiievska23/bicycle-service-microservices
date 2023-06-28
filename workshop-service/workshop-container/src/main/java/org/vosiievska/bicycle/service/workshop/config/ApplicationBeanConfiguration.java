package org.vosiievska.bicycle.service.workshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vosiievska.bicycle.service.workshop.WorkshopDomainService;
import org.vosiievska.bicycle.service.workshop.WorkshopDomainServiceImpl;

@Configuration
public class ApplicationBeanConfiguration {

  @Bean
  public WorkshopDomainService workshopDomainService() {
    return new WorkshopDomainServiceImpl();
  }
}
