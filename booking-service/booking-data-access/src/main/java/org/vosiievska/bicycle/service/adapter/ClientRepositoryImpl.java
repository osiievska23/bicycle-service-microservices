package org.vosiievska.bicycle.service.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;
import org.vosiievska.bicycle.service.entity.Client;
import org.vosiievska.bicycle.service.mapper.ClientMapper;
import org.vosiievska.bicycle.service.repository.ClientJpaRepository;
import org.vosiievska.bicycle.service.repository.ClientRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

  private final ClientJpaRepository clientJpaRepository;
  private final ClientMapper clientMapper;

  @Override
  public Optional<Client> findById(ClientId clientId) {
    return clientJpaRepository.findById(clientId.getValue())
        .map(clientMapper::jpaEntityToClient);
  }

  @Override
  public boolean existsById(ClientId clientId) {
    return clientJpaRepository.existsById(clientId.getValue());
  }
}
