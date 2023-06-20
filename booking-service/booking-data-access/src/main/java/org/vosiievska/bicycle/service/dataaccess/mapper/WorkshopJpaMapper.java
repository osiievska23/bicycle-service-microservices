package org.vosiievska.bicycle.service.dataaccess.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.vosiievska.bicycle.service.dataaccess.entity.WorkshopEntity;
import org.vosiievska.bicycle.service.domain.core.entity.Workshop;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;

@Mapper(
    componentModel = "spring",
    uses = {AddressJpaMapper.class, SpecialistJpaMapper.class})
public abstract class WorkshopJpaMapper {

  public abstract Workshop jpaEntityToWorkshop(WorkshopEntity jpaEntity);

  @AfterMapping
  void jpaEntityToWorkshop(@MappingTarget Workshop workshop, WorkshopEntity jpaEntity) {
    workshop.setId(new WorkshopId(jpaEntity.getId()));
  }
}
