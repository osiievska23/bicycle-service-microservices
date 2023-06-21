package org.vosiievska.bicycle.service.dataaccess.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.dataaccess.entity.AddressEntity;
import org.vosiievska.bicycle.service.domain.valueobject.Address;

import java.util.UUID;

@Mapper(
    componentModel = "spring",
    imports = UUID.class
)
public interface AddressJpaMapper {

  Address jpaEntityToAddress(AddressEntity jpaEntity);


  @Mapping(target = "id", expression = "java(address.getId() != null ? address.getId() : UUID.randomUUID())")
  AddressEntity addressToJpaEntity(Address address);
}
