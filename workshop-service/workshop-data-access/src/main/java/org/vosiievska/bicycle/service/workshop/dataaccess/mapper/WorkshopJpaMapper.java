package org.vosiievska.bicycle.service.workshop.dataaccess.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.dataaccess.jpa.entity.WorkshopEntity;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;
import org.vosiievska.bicycle.service.workshop.entity.Workshop;

@Mapper(
    componentModel = "spring",
    uses = {SpecialistJpaMapper.class},
    imports = WorkshopId.class
)
public interface WorkshopJpaMapper {

  @Mapping(target = "id", expression = "java(new WorkshopId(jpaEntity.getId()))")
  Workshop jpaEntityToWorkshop(WorkshopEntity jpaEntity);

}
