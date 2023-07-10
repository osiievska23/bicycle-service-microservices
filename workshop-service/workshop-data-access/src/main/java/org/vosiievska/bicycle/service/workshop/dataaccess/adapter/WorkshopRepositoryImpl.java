package org.vosiievska.bicycle.service.workshop.dataaccess.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.workshop.dataaccess.mapper.WorkshopJpaMapper;
import org.vosiievska.bicycle.service.workshop.dataaccess.repository.SpecialistJpaRepository;
import org.vosiievska.bicycle.service.workshop.dataaccess.repository.WorkshopJpaRepository;
import org.vosiievska.bicycle.service.workshop.entity.Workshop;
import org.vosiievska.bicycle.service.workshop.repository.WorkshopRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WorkshopRepositoryImpl implements WorkshopRepository {

  private final WorkshopJpaRepository workshopJpaRepository;
  private final SpecialistJpaRepository specialistJpaRepository;
  private final WorkshopJpaMapper workshopJpaMapper;

  @Override
  public Optional<Workshop> findAvailableWorkshopWithAvailableSpecialist() {
    return workshopJpaRepository.findAvailableWorkshopsWithAvailableSpecialist().stream()
        .findFirst()
        .map(workshopJpaMapper::jpaEntityToWorkshop);
  }

  @Override
  public void updateSpecialistStatusById(SpecialistId specialistId, boolean busy) {
    specialistJpaRepository.updateSpecialistStatusById(specialistId.getValue(), busy);
  }

  @Override
  public void updateSpecialistStatusByBookingId(BookingId bookingId, boolean busy) {
    specialistJpaRepository.updateSpecialistStatusByBookingId(bookingId.getValue(), busy);
  }
}
