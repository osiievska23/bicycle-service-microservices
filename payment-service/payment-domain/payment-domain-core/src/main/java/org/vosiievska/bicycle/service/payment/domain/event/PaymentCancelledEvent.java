package org.vosiievska.bicycle.service.payment.domain.event;

import org.vosiievska.bicycle.service.payment.domain.entity.Payment;

public class PaymentCancelledEvent extends PaymentEvent {

  public PaymentCancelledEvent(Payment domain) {
    super(domain);
  }
}
