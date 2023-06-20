package org.vosiievska.bicycle.service.dataaccess.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.vosiievska.bicycle.service.dataaccess.mapper.RepairServiceJpaMapper;
import org.vosiievska.bicycle.service.dataaccess.repository.RepairServiceJpaRepository;
import org.vosiievska.bicycle.service.domain.core.entity.RepairService;
import org.vosiievska.bicycle.service.domain.service.repository.RepairServiceRepository;
import org.vosiievska.bicycle.service.domain.valueobject.RepairServiceId;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RepairServiceRepositoryImpl implements RepairServiceRepository {

  private final RepairServiceJpaRepository repairServiceJpaRepository;
  private final RepairServiceJpaMapper repairServiceMapper;

  @Override
  public Optional<RepairService> findById(RepairServiceId id) {
    return repairServiceJpaRepository.findById(id.getValue())
        .map(repairServiceMapper::jpaEntityToRepairService);
  }
}
