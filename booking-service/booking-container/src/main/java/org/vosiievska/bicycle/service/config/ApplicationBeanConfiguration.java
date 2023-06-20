package org.vosiievska.bicycle.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vosiievska.bicycle.service.domain.core.BookingDomainService;
import org.vosiievska.bicycle.service.domain.core.BookingDomainServiceImpl;

@Configuration
public class ApplicationBeanConfiguration {

  /**
   * Domain service embeds significant business rules. Declared as a bean directly since the domain module components
   * are not supposed to be registered as a service by its own configuration but to hold domain logic only.
   * @return BookingDomainService
   */
  @Bean
  public BookingDomainService bookingDomainService() {
    return new BookingDomainServiceImpl();
  }
}
