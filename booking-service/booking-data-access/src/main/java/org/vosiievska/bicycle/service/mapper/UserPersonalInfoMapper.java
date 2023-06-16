package org.vosiievska.bicycle.service.mapper;

import org.mapstruct.Mapper;
import org.vosiievska.bicycle.service.entity.BaseUserEntity;
import org.vosiievska.bicycle.service.valueobject.UserPersonalInfo;

@Mapper(componentModel = "spring")
public interface UserPersonalInfoMapper {

  UserPersonalInfo jpaEntityToUserPersonalInfo(BaseUserEntity jpaEntity);

}
