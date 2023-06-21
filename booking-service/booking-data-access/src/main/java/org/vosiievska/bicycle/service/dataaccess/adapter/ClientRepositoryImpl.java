package org.vosiievska.bicycle.service.dataaccess.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.vosiievska.bicycle.service.dataaccess.mapper.ClientJpaMapper;
import org.vosiievska.bicycle.service.dataaccess.repository.ClientJpaRepository;
import org.vosiievska.bicycle.service.domain.core.entity.Client;
import org.vosiievska.bicycle.service.domain.service.repository.ClientRepository;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

  private final ClientJpaRepository clientJpaRepository;
  private final ClientJpaMapper clientJpaMapper;

  @Override
  public Optional<Client> findById(ClientId clientId) {
    return clientJpaRepository.findById(clientId.getValue())
        .map(clientJpaMapper::jpaEntityToClient);
  }

  @Override
  public boolean existsById(ClientId clientId) {
    return clientJpaRepository.existsById(clientId.getValue());
  }

  @Override
  public Optional<Client> findClientWithAddressById(UUID clientId) {
    return clientJpaRepository.findClientWithAddressById(clientId)
        .map(clientJpaMapper::jpaEntityToClient);
  }
}
