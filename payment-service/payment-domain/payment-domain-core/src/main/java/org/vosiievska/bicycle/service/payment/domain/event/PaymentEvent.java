package org.vosiievska.bicycle.service.payment.domain.event;

import org.vosiievska.bicycle.service.domain.event.AbstractDomainEvent;
import org.vosiievska.bicycle.service.payment.domain.entity.Payment;

import java.time.LocalDateTime;
import java.util.List;

public abstract class PaymentEvent extends AbstractDomainEvent<Payment> {
  public PaymentEvent(Payment domain, LocalDateTime createdAt, List<String> failureMessages) {
    super(domain, createdAt, failureMessages);
  }

  public PaymentEvent(Payment domain, List<String> failureMessages) {
    super(domain, failureMessages);
  }

  public PaymentEvent(Payment domain) {
    super(domain);
  }

  public Payment getPayment() {
    return super.getDomain();
  }
}
