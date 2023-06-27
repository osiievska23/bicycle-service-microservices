package org.vosiievska.bicycle.service.payment.domain.event;

import org.vosiievska.bicycle.service.payment.domain.entity.Payment;

public class PaymentCompleteEvent extends PaymentEvent {

  public PaymentCompleteEvent(Payment domain) {
    super(domain);
  }
}
