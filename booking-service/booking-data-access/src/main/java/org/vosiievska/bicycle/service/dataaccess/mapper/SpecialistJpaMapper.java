package org.vosiievska.bicycle.service.dataaccess.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.dataaccess.jpa.entity.SpecialistEntity;
import org.vosiievska.bicycle.service.domain.core.entity.Specialist;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;

@Mapper(
    componentModel = "spring",
    uses = {UserPersonalInfoJpaMapper.class},
    imports = {WorkshopId.class, SpecialistId.class}
)
public interface SpecialistJpaMapper {

  @Mapping(target = "id", expression = "java(new SpecialistId(jpaEntity.getId()))")
  @Mapping(target = "workshopId", expression = "java(new WorkshopId(jpaEntity.getWorkshopId()))")
  @Mapping(target = "specialistInfo", source = "jpaEntity")
  abstract Specialist jpaEntityToSpecialist(SpecialistEntity jpaEntity);

}
