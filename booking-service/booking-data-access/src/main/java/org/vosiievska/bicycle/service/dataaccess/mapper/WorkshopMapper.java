package org.vosiievska.bicycle.service.dataaccess.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.dataaccess.entity.WorkshopEntity;
import org.vosiievska.bicycle.service.domain.core.entity.Workshop;

@Mapper(
    componentModel = "spring",
    uses = {AddressMapper.class, SpecialistMapper.class})
public interface WorkshopMapper {

  @Mapping(target = "id", expression = "java(new WorkshopId(jpaEntity.getId()))")
  Workshop jpaEntityToWorkshop(WorkshopEntity jpaEntity);
}
