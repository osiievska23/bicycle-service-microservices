package org.vosiievska.bicycle.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vosiievska.bicycle.service.entity.RepairServiceEntity;

import java.util.Optional;

@Repository
public interface RepairServiceJpaRepository extends JpaRepository<RepairServiceEntity, String> {

  Optional<RepairServiceEntity> findById(String title);

}
