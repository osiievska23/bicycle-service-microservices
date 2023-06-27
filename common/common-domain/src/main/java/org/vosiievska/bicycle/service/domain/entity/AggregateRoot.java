package org.vosiievska.bicycle.service.domain.entity;

import org.vosiievska.bicycle.service.domain.valueobject.BaseId;

/**
 * This class is used to define an aggregate root.
 * @param <ID> unique immutable identifier
 */
public abstract class AggregateRoot<ID extends BaseId<?>> extends BaseEntity<ID> {
}
