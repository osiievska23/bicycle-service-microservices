package org.vosiievska.bicycle.service.mapper;


import org.mapstruct.Mapper;
import org.vosiievska.bicycle.service.domain.valueobject.Address;
import org.vosiievska.bicycle.service.dto.AddressDto;

@Mapper(componentModel = "spring")
public interface AddressMapper {

  Address addressDtoToValueObject(AddressDto dto);

}
