package org.vosiievska.bicycle.service.domain.service.repository;

import org.vosiievska.bicycle.service.domain.core.entity.Client;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;

import java.util.Optional;

/**
 * Secondary port according to the Hexagonal Architecture
 */
public interface ClientRepository {

  Optional<Client> findById(ClientId clientId);

  boolean existsById(ClientId clientId);
}
