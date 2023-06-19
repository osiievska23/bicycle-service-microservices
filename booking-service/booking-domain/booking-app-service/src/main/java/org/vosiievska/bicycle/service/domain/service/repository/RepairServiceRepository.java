package org.vosiievska.bicycle.service.domain.service.repository;

import org.vosiievska.bicycle.service.domain.core.entity.RepairService;
import org.vosiievska.bicycle.service.domain.valueobject.RepairServiceId;

import java.util.Optional;

/**
 * Secondary port according to the Hexagonal Architecture
 */
public interface RepairServiceRepository {

  Optional<RepairService> findById(RepairServiceId id);
}
