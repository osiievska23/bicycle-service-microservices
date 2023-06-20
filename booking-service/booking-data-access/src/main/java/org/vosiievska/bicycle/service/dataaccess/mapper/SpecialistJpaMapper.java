package org.vosiievska.bicycle.service.dataaccess.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.vosiievska.bicycle.service.dataaccess.entity.SpecialistEntity;
import org.vosiievska.bicycle.service.domain.core.entity.Specialist;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;

@Mapper(
    componentModel = "spring",
    uses = {UserPersonalInfoJpaMapper.class},
    imports = WorkshopId.class
)
public abstract class SpecialistJpaMapper {

  @Mapping(target = "workshopId", expression = "java(new WorkshopId(jpaEntity.getWorkshopId()))")
  @Mapping(target = "specialistInfo", source = "jpaEntity")
//  @Mapping(target = "specialistInfo.firstName", source = "firstName")
//  @Mapping(target = "specialistInfo.lastName", source = "lastName")
//  @Mapping(target = "specialistInfo.dob", source = "dob")
  abstract Specialist jpaEntityToSpecialist(SpecialistEntity jpaEntity);

  @AfterMapping
  void jpaEntityToSpecialist(@MappingTarget Specialist specialist, SpecialistEntity jpaEntity) {
    specialist.setId(new SpecialistId(jpaEntity.getId()));
  }
}
