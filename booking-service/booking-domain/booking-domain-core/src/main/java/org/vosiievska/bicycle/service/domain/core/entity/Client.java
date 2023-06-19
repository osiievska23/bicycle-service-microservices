package org.vosiievska.bicycle.service.domain.core.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.core.valueobject.UserPersonalInfo;
import org.vosiievska.bicycle.service.domain.entity.AggregateRoot;
import org.vosiievska.bicycle.service.domain.valueobject.Address;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;

import java.util.UUID;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Client extends AggregateRoot<ClientId> {

  final UserPersonalInfo clientInfo;
  final Address address;

  public UUID getIdValue() {
    return getId().getValue();
  }
}
