package org.vosiievska.bicycle.service.workshop.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.entity.BaseEntity;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Specialist extends BaseEntity<SpecialistId> {

  final WorkshopId workshopId;
  final boolean busy;
}
