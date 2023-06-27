package org.vosiievska.bicycle.service.payment.domain.event;

import org.vosiievska.bicycle.service.payment.domain.entity.Payment;

import java.util.List;

public class PaymentFailedEvent extends PaymentEvent {

  public PaymentFailedEvent(Payment domain, List<String> failureMessages) {
    super(domain, failureMessages);
  }
}
