package org.vosiievska.bicycle.service.payment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vosiievska.bicycle.service.payment.domain.PaymentDomainService;
import org.vosiievska.bicycle.service.payment.domain.PaymentDomainServiceImpl;

@Configuration
public class ApplicationBeanConfiguration {

  @Bean
  public PaymentDomainService paymentDomainService() {
    return new PaymentDomainServiceImpl();
  }
}
