package org.vosiievska.bicycle.service.domain.service.mapper;


import org.mapstruct.Mapper;
import org.vosiievska.bicycle.service.domain.service.dto.AddressDto;
import org.vosiievska.bicycle.service.domain.valueobject.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

  Address addressDtoToValueObject(AddressDto dto);

}
