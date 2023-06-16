package org.vosiievska.bicycle.service.domain.valueobject;

import java.util.UUID;

public class SpecialistId extends BaseId<UUID> {
  public SpecialistId(UUID documentSerialNumber) {
    super(documentSerialNumber);
  }
}
