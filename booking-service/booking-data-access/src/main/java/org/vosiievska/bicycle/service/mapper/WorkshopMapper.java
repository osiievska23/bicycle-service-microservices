package org.vosiievska.bicycle.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.entity.Workshop;
import org.vosiievska.bicycle.service.entity.WorkshopEntity;

@Mapper(
    componentModel = "spring",
    uses = {AddressMapper.class, SpecialistMapper.class})
public interface WorkshopMapper {

  @Mapping(target = "id", expression = "java(new WorkshopId(jpaEntity.getId()))")
  Workshop jpaEntityToWorkshop(WorkshopEntity jpaEntity);
}
