package org.vosiievska.bicycle.service.repository;

import org.vosiievska.bicycle.service.domain.valueobject.ClientId;
import org.vosiievska.bicycle.service.entity.Client;

import java.util.Optional;

/**
 * Secondary port according to the Hexagonal Architecture
 */
public interface ClientRepository {

  Optional<Client> findById(ClientId clientId);

  boolean existsById(ClientId clientId);
}
