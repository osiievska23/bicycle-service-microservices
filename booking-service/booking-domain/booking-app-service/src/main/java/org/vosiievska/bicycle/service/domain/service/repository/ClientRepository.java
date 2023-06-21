package org.vosiievska.bicycle.service.domain.service.repository;

import org.vosiievska.bicycle.service.domain.core.entity.Client;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;

import java.util.Optional;
import java.util.UUID;

/**
 * Secondary port according to the Hexagonal Architecture
 */
public interface ClientRepository {

  Optional<Client> findById(ClientId clientId);

  boolean existsById(ClientId clientId);

  Optional<Client> findClientWithAddressById(UUID clientId);
}
