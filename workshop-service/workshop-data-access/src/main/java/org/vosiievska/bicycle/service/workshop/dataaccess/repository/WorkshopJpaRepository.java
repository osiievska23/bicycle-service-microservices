package org.vosiievska.bicycle.service.workshop.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vosiievska.bicycle.service.dataaccess.jpa.entity.WorkshopEntity;

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

  @Modifying
  @Query(nativeQuery = true,
      value = "update service.specialist set busy = ?2 "
          + "where id = (select specialist_id from service.booking as b where b.id = ?1);")
  void updateSpecialistStatusByBookingId(UUID specialistId, boolean busy);
}
