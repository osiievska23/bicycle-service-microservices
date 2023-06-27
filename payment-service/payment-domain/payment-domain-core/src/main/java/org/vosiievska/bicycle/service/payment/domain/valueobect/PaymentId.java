package org.vosiievska.bicycle.service.payment.domain.valueobect;

import org.vosiievska.bicycle.service.domain.valueobject.BaseId;

import java.util.UUID;

public class PaymentId extends BaseId<UUID> {
  public PaymentId(UUID value) {
        super(value);
    }
}
