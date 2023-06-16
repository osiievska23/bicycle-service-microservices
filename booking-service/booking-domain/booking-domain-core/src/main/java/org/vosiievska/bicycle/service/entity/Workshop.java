package org.vosiievska.bicycle.service.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.entity.AggregateRoot;
import org.vosiievska.bicycle.service.domain.valueobject.Address;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;
import org.vosiievska.bicycle.service.exception.BookingDomainException;

import java.util.Set;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Workshop extends AggregateRoot<WorkshopId> {

  final Address address;
  final Set<Specialist> specialists;
  final boolean available;

  public Integer getIdValue() {
    return getId().getValue();
  }

  public void validateWorkshop() {
    if (!available) {
      throw new BookingDomainException("Workshop with id '%s' is not available!");
    }
    if (specialists.stream().anyMatch(Specialist::isBusy)) {
      throw new BookingDomainException("Workshop with id '%s' does not have available specialists!");
    }
  }
}
