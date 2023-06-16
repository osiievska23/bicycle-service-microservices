package org.vosiievska.bicycle.service.valueobject;

import org.vosiievska.bicycle.service.domain.valueobject.BaseId;

import java.util.UUID;

public class BookingTrackingId extends BaseId<UUID> { //todo: delete

  public BookingTrackingId(UUID value) {
    super(value);
  }
}
