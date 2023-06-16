package org.vosiievska.bicycle.service.mapper;

import org.mapstruct.Mapper;
import org.vosiievska.bicycle.service.entity.RepairService;
import org.vosiievska.bicycle.service.entity.RepairServiceEntity;

@Mapper(componentModel = "spring")
public interface RepairServiceMapper {

  RepairService jpaEntityToRepairService(RepairServiceEntity jpaEntity);

  RepairServiceEntity repairServiceToJpaEntity(RepairService repairService);
}
