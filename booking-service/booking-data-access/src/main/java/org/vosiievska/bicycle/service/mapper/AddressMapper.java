package org.vosiievska.bicycle.service.mapper;

import org.mapstruct.Mapper;
import org.vosiievska.bicycle.service.domain.valueobject.Address;
import org.vosiievska.bicycle.service.entity.AddressEntity;

@Mapper(componentModel = "spring")
public interface AddressMapper {

  Address jpaEntityToAddress(AddressEntity jpaEntity);


  AddressEntity addressToJpaEntity(Address address);
}
