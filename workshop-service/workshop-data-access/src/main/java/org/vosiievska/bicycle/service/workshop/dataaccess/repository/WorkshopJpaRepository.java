package org.vosiievska.bicycle.service.workshop.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vosiievska.bicycle.service.dataaccess.jpa.entity.WorkshopEntity;

import java.util.Set;

@Repository
public interface WorkshopJpaRepository extends JpaRepository<WorkshopEntity, Integer> {

  @Query("select w from WorkshopEntity w "
        + "inner join fetch w.specialists s " // fixes N + 1 problem
        + "where w.available = true and s.busy = false")
  Set<WorkshopEntity> findAvailableWorkshopsWithAvailableSpecialist();
}
