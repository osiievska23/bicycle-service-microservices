package org.vosiievska.bicycle.service.dataaccess.mapper;

import org.mapstruct.Mapper;
import org.vosiievska.bicycle.service.dataaccess.entity.RepairServiceEntity;
import org.vosiievska.bicycle.service.domain.core.entity.RepairService;

@Mapper(componentModel = "spring")
public interface RepairServiceMapper {

  RepairService jpaEntityToRepairService(RepairServiceEntity jpaEntity);

  RepairServiceEntity repairServiceToJpaEntity(RepairService repairService);
}
