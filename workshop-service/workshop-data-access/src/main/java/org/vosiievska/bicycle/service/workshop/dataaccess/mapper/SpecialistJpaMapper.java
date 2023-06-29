package org.vosiievska.bicycle.service.workshop.dataaccess.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.dataaccess.jpa.entity.SpecialistEntity;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;
import org.vosiievska.bicycle.service.workshop.entity.Specialist;

@Mapper(
    componentModel = "spring",
    imports = {WorkshopId.class, SpecialistId.class}
)
public interface SpecialistJpaMapper {

  @Mapping(target = "id", expression = "java(new SpecialistId(jpaEntity.getId()))")
  @Mapping(target = "workshopId", expression = "java(new WorkshopId(jpaEntity.getWorkshopId()))")
  Specialist jpaEntityToSpecialist(SpecialistEntity jpaEntity);

}
