package org.vosiievska.bicycle.service.domain.valueobject;

import java.util.UUID;

public class BookingId extends BaseId<UUID> {

  public BookingId(UUID value) {
    super(value);
  }
}
