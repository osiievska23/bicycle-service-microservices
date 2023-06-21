package org.vosiievska.bicycle.service.domain.core.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.core.valueobject.UserPersonalInfo;
import org.vosiievska.bicycle.service.domain.entity.Entity;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Specialist extends Entity<SpecialistId> {

  final WorkshopId workshopId;
  final UserPersonalInfo specialistInfo;
  final boolean busy;
}
