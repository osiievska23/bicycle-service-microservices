package org.vosiievska.bicycle.service.workshop.valueobject;

import org.vosiievska.bicycle.service.domain.valueobject.BaseId;

import java.util.UUID;

public class BookingApprovalId extends BaseId<UUID> {

  public BookingApprovalId(UUID value) {
    super(value);
  }
}
