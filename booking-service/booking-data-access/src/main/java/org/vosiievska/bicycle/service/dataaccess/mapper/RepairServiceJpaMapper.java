package org.vosiievska.bicycle.service.dataaccess.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.dataaccess.entity.RepairServiceEntity;
import org.vosiievska.bicycle.service.domain.core.entity.RepairService;

@Mapper(componentModel = "spring")
public interface RepairServiceJpaMapper {

  @Mapping(target = "price", expression = "java(new Price(jpaEntity.getPrice()))")
  RepairService jpaEntityToRepairService(RepairServiceEntity jpaEntity);

  @Mapping(target = "price", source = "repairService.price.amount")
  RepairServiceEntity repairServiceToJpaEntity(RepairService repairService);
}
