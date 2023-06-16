package org.vosiievska.bicycle.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vosiievska.bicycle.service.entity.ClientEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientJpaRepository extends JpaRepository<ClientEntity, UUID> {

  Optional<ClientEntity> findById(UUID id);

}
