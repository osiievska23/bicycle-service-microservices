package org.vosiievska.bicycle.service.dataaccess.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.vosiievska.bicycle.service.dataaccess.jpa.entity.ClientEntity;
import org.vosiievska.bicycle.service.domain.core.entity.Client;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;

@Mapper(
    componentModel = "spring",
    uses = {AddressJpaMapper.class, UserPersonalInfoJpaMapper.class},
    imports = ClientId.class)
public abstract class ClientJpaMapper {

  @Mapping(target = "id", expression = "java(new ClientId(jpaEntity.getId()))")
  @Mapping(target = "clientInfo", source = "jpaEntity")
  public abstract Client jpaEntityToClient(ClientEntity jpaEntity);

  @AfterMapping
  void jpaEntityToClient(@MappingTarget Client client, ClientEntity jpaEntity) {
    client.setId(new ClientId(jpaEntity.getId()));
  }
}
