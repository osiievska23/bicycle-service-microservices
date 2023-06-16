package org.vosiievska.bicycle.service.domain.event;

import java.io.Serializable;

/**
 * A Domain Event should be represented explicitly.
 * Domain events are used to notify other services when something happens. This service implementations would represent
 * domain events related to a specific entity.
 */
public interface DomainEvent extends Serializable {

}
