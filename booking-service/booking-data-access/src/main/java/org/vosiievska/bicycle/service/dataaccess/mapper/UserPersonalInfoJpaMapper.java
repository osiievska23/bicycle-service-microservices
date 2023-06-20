package org.vosiievska.bicycle.service.dataaccess.mapper;

import org.mapstruct.Mapper;
import org.vosiievska.bicycle.service.dataaccess.entity.BaseUserEntity;
import org.vosiievska.bicycle.service.domain.core.valueobject.UserPersonalInfo;

@Mapper(componentModel = "spring")
public interface UserPersonalInfoJpaMapper {

  UserPersonalInfo jpaEntityToUserPersonalInfo(BaseUserEntity jpaEntity);

}
