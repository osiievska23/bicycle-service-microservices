package org.vosiievska.bicycle.service.domain.valueobject;

import java.util.UUID;

public class ClientId extends BaseId<UUID> {
  public ClientId(UUID value) {
    super(value);
  }
}
