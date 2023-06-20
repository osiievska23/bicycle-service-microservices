package org.vosiievska.bicycle.service.dataaccess.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.vosiievska.bicycle.service.dataaccess.mapper.WorkshopJpaMapper;
import org.vosiievska.bicycle.service.dataaccess.repository.WorkshopJpaRepository;
import org.vosiievska.bicycle.service.domain.core.entity.Workshop;
import org.vosiievska.bicycle.service.domain.service.repository.WorkshopRepository;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WorkshopRepositoryImpl implements WorkshopRepository {

  private final WorkshopJpaRepository workshopJpaRepository;
  private final WorkshopJpaMapper workshopJpaMapper;

  @Override
  public Optional<Workshop> findAvailableWorkshopWithAvailableSpecialist() {
    return workshopJpaRepository.findAvailableWorkshopWithAvailableSpecialist()
        .map(workshopJpaMapper::jpaEntityToWorkshop);
  }

  @Override
  public void updateSpecialistStatusById(SpecialistId specialistId, boolean busy) {
    workshopJpaRepository.updateSpecialistStatusById(specialistId.getValue(), busy);
  }
}
