package org.vosiievska.bicycle.service.repository;

import org.vosiievska.bicycle.service.domain.valueobject.RepairServiceId;
import org.vosiievska.bicycle.service.entity.RepairService;

import java.util.Optional;

/**
 * Secondary port according to the Hexagonal Architecture
 */
public interface RepairServiceRepository {

  Optional<RepairService> findById(RepairServiceId id);
}
