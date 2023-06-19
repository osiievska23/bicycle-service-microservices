package org.vosiievska.bicycle.service.dataaccess.mapper;

import org.mapstruct.Mapper;
import org.vosiievska.bicycle.service.dataaccess.entity.AddressEntity;
import org.vosiievska.bicycle.service.domain.valueobject.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

  Address jpaEntityToAddress(AddressEntity jpaEntity);


  AddressEntity addressToJpaEntity(Address address);
}
