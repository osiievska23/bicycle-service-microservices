package org.vosiievska.bicycle.service.workshop.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.entity.AggregateRoot;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;

import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Workshop extends AggregateRoot<WorkshopId> {

  final Set<Specialist> specialists;
  final boolean available;

  public Integer getIdValue() {
    return getId().getValue();
  }

  public void validateWorkshop(List<String> failureMessages) {
    validateIsActive(failureMessages);
    validateAvailableSpecialist(failureMessages);
  }

  private void validateIsActive(List<String> failureMessages) {
    if (!available) {
      failureMessages.add(String.format("Workshop with id '%s' is not available!", getIdValue()));
    }
  }

  private void validateAvailableSpecialist(List<String> failureMessages) {
    if (specialists.stream().allMatch(Specialist::isBusy)) {
      failureMessages.add(String.format("Workshop with id '%s' does not have available specialists!", getIdValue()));
    }
  }
}
