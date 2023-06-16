package org.vosiievska.bicycle.service.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.entity.Entity;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;
import org.vosiievska.bicycle.service.valueobject.UserPersonalInfo;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Specialist extends Entity<SpecialistId> {

  final WorkshopId workshopId;
  final UserPersonalInfo specialistInfo;
  final boolean busy;
}
