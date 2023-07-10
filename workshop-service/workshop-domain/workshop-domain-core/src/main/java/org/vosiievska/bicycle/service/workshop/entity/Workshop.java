package org.vosiievska.bicycle.service.workshop.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.entity.AggregateRoot;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Workshop extends AggregateRoot<WorkshopId> {

  final Set<Specialist> specialists;
  final boolean available;

  List<String> failureMessages;

  public UUID getIdValue() {
    return getId().getValue();
  }

  public void validateWorkshop() {
    this.failureMessages = new ArrayList<>();
    validateIsActive();
    validateAvailableSpecialist();
  }

  private void validateIsActive() {
    if (!available) {
      this.failureMessages.add(String.format("Workshop with id '%s' is not available!", getIdValue()));
    }
  }

  private void validateAvailableSpecialist() {
    if (specialists.stream().allMatch(Specialist::isBusy)) {
      this.failureMessages.add(String.format("Workshop with id '%s' does not have available specialists!", getIdValue()));
    }
  }
}
