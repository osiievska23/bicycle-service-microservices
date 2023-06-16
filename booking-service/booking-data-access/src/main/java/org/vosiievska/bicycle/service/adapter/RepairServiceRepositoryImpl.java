package org.vosiievska.bicycle.service.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.vosiievska.bicycle.service.domain.valueobject.RepairServiceId;
import org.vosiievska.bicycle.service.entity.RepairService;
import org.vosiievska.bicycle.service.mapper.RepairServiceMapper;
import org.vosiievska.bicycle.service.repository.RepairServiceJpaRepository;
import org.vosiievska.bicycle.service.repository.RepairServiceRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RepairServiceRepositoryImpl implements RepairServiceRepository {

  private final RepairServiceJpaRepository repairServiceJpaRepository;
  private final RepairServiceMapper repairServiceMapper;

  @Override
  public Optional<RepairService> findById(RepairServiceId id) {
    return repairServiceJpaRepository.findById(id.getValue())
        .map(repairServiceMapper::jpaEntityToRepairService);
  }
}
