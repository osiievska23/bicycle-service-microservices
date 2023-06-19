package org.vosiievska.bicycle.service.dataaccess.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.dataaccess.entity.ClientEntity;
import org.vosiievska.bicycle.service.domain.core.entity.Client;

@Mapper(
    componentModel = "spring",
    uses = {AddressMapper.class, UserPersonalInfoMapper.class})
public interface ClientMapper {

  @Mapping(target = "id", expression = "java(new ClientId(jpaEntity.getId()))")
  Client jpaEntityToClient(ClientEntity jpaEntity);

}
