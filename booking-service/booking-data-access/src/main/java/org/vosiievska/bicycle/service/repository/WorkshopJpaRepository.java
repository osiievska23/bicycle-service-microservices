package org.vosiievska.bicycle.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.entity.WorkshopEntity;

import java.util.Optional;

@Repository
public interface WorkshopJpaRepository extends JpaRepository<WorkshopEntity, Integer> {

  @Query(nativeQuery = true,
      value = "select * from workshop w "
          + "where w.available = true "
          + "and exists (select s.id from specialist s where s.workshop_id = w.id and s.busy = false) "
          + "limit 1;")
  Optional<WorkshopEntity> findAvailableWorkshopWithAvailableSpecialist();

  @Modifying
  @Query("update SpecialistEntity s set s.busy = true where s.id = :specialistId")
  void makeSpecialistBusyById(SpecialistId specialistId);
}
