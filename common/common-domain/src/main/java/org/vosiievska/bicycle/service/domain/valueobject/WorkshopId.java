package org.vosiievska.bicycle.service.domain.valueobject;

import java.util.UUID;

public class WorkshopId extends BaseId<UUID> {
  public WorkshopId(UUID departmentNumber) {
    super(departmentNumber);
  }
}
