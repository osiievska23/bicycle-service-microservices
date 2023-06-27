package org.vosiievska.bicycle.service.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vosiievska.bicycle.service.dataaccess.jpa.entity.ClientEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientJpaRepository extends JpaRepository<ClientEntity, UUID> {

  Optional<ClientEntity> findById(UUID id);

@Query("select c from ClientEntity c "
      + "join c.address a "
      + "where c.id = ?1")
  Optional<ClientEntity> findClientWithAddressById(UUID clientId);
}
