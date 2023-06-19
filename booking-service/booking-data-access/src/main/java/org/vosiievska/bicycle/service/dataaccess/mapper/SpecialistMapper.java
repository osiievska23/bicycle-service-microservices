package org.vosiievska.bicycle.service.dataaccess.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.dataaccess.entity.SpecialistEntity;
import org.vosiievska.bicycle.service.domain.core.entity.Specialist;

@Mapper(
    componentModel = "spring",
    uses = {UserPersonalInfoMapper.class})
public interface SpecialistMapper {

  @Mapping(target = "id", expression = "java(new SpecialistId(jpaEntity.getId()))")
  @Mapping(target = "workshopId", expression = "java(new WorkshopId(jpaEntity.getWorkshopId()))")
  Specialist jpaEntityToSpecialist(SpecialistEntity dto);

}
