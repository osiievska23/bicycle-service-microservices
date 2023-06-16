package org.vosiievska.bicycle.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.entity.Client;
import org.vosiievska.bicycle.service.entity.ClientEntity;

@Mapper(
    componentModel = "spring",
    uses = {AddressMapper.class, UserPersonalInfoMapper.class})
public interface ClientMapper {

  @Mapping(target = "id", expression = "java(new ClientId(jpaEntity.getId()))")
  Client jpaEntityToClient(ClientEntity jpaEntity);

}
