package org.vosiievska.bicycle.service.domain.service.repository;

import org.vosiievska.bicycle.service.domain.core.entity.Workshop;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;

import java.util.Optional;

/**
 * Secondary port according to the Hexagonal Architecture
 */
public interface WorkshopRepository {

  Optional<Workshop> findAvailableWorkshopWithAvailableSpecialist();

  void updateSpecialistStatusById(SpecialistId specialistId, boolean busy);
}