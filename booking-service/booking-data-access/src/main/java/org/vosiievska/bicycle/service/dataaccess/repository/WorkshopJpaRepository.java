package org.vosiievska.bicycle.service.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vosiievska.bicycle.service.dataaccess.entity.WorkshopEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkshopJpaRepository extends JpaRepository<WorkshopEntity, Integer> {

  @Query(nativeQuery = true,
      value = "select * from service.workshop w "
          + "where w.available = true "
          + "and exists (select s.id from service.specialist s where s.workshop_id = w.id and s.busy = false) "
          + "limit 1;")
  Optional<WorkshopEntity> findAvailableWorkshopWithAvailableSpecialist();

  @Modifying
  @Query("update SpecialistEntity s set s.busy = ?2 where s.id = ?1")
  void updateSpecialistStatusById(UUID specialistId, boolean busy);
}
