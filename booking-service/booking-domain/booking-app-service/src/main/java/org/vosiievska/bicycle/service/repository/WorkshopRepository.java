package org.vosiievska.bicycle.service.repository;

import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.entity.Workshop;

import java.util.Optional;

/**
 * Secondary port according to the Hexagonal Architecture
 */
public interface WorkshopRepository {

  Optional<Workshop> findAvailableWorkshopWithAvailableSpecialist();

  void updateSpecialistStatusById(SpecialistId specialistId, boolean busy);
}
