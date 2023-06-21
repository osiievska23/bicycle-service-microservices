package org.vosiievska.bicycle.service.dataaccess.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.dataaccess.entity.WorkshopEntity;
import org.vosiievska.bicycle.service.domain.core.entity.Workshop;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;

@Mapper(
    componentModel = "spring",
    uses = {AddressJpaMapper.class, SpecialistJpaMapper.class},
    imports = WorkshopId.class
)
public interface WorkshopJpaMapper {

  @Mapping(target = "id", expression = "java(new WorkshopId(jpaEntity.getId()))")
  public abstract Workshop jpaEntityToWorkshop(WorkshopEntity jpaEntity);

}
