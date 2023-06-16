package org.vosiievska.bicycle.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.entity.Specialist;
import org.vosiievska.bicycle.service.entity.SpecialistEntity;

@Mapper(
    componentModel = "spring",
    uses = {UserPersonalInfoMapper.class})
public interface SpecialistMapper {

  @Mapping(target = "id", expression = "java(new SpecialistId(jpaEntity.getId()))")
  @Mapping(target = "workshopId", expression = "java(new WorkshopId(jpaEntity.getWorkshopId()))")
  Specialist jpaEntityToSpecialist(SpecialistEntity dto);

}
