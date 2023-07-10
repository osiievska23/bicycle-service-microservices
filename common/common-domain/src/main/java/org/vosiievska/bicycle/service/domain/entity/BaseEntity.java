package org.vosiievska.bicycle.service.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.vosiievska.bicycle.service.domain.valueobject.BaseId;

@Getter
@Setter
@EqualsAndHashCode
public abstract class BaseEntity<ID extends BaseId<?>> {

  private ID id;

}
