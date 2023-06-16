package org.vosiievska.bicycle.service.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.vosiievska.bicycle.service.domain.valueobject.BaseId;

/** // TODO: remove
 * Entities are used to implement the critical business rules.
 * Entities must have a unique immutable identifier of any type, which may be shared among bounded contexts.
 * The entities themselves don’t need to be identical across every BC.
 * Recommendation: try to avoid using primitives when implement the domain entity. It's better to use value objects and sub-entities
 * to support domain entity consistency.  Each context is allowed to have a private version of a given entity.
 *
 * It's not necessary to implement equals() and hashCode() methods in the child class beacuse it's already implemented in
 * parent class and evaluate the entity identity.
 *
 * @param <ID> unique immutable identifier
 */
@Getter
@Setter
@EqualsAndHashCode
public abstract class Entity<ID extends BaseId<?>> {

  private ID id;

}
