package org.vosiievska.bicycle.service.domain.core.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.core.exception.BookingDomainException;
import org.vosiievska.bicycle.service.domain.entity.AggregateRoot;
import org.vosiievska.bicycle.service.domain.valueobject.Address;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.vosiievska.bicycle.service.domain.valueobject.BookingStatus.PENDING;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking extends AggregateRoot<BookingId> {

  final ClientId clientId;
  final RepairService repairService;
  final Address clientAddress;

  WorkshopId workshopId;
  SpecialistId specialistId;

  BookingStatus currentStatus;
  Instant updatedAt;
  List<String> failureMessages;

  public UUID getIdValue() {
    return getId().getValue();
  }

  public void initBooking() {
    super.setId(new BookingId(UUID.randomUUID()));
    this.failureMessages = new ArrayList<>();
    updateCurrentStatus(PENDING);
  }
  public void updateCurrentStatus(BookingStatus status) {
    validateNextBookingStatus(status);
    this.currentStatus = status;
    this.updatedAt = Instant.now();
  }

  public void updateCurrentStatusWithFailure(BookingStatus status, List<String> failureMessages) {
    updateCurrentStatus(status);
    updateFailureMessages(failureMessages);
  }

  private void updateFailureMessages(List<String> failureMessages) {
    if (Objects.nonNull(failureMessages)) {
      this.failureMessages.addAll(failureMessages);
    }
  }

  private void validateNextBookingStatus(BookingStatus newStatus) {
    if (currentStatus == null && PENDING.equals(newStatus)) {
      return;
    }
    if (currentStatus == null || !currentStatus.isNextStatusValid(newStatus)) {
      throw new BookingDomainException(
          "Updating booking status from '%s' to '%s' is not allowed!", this.currentStatus.name(), newStatus.name());
    }
  }

  public void validateBooking() {
    validateBookingStatus();
    repairService.validatePrice();
  }

  private void validateBookingStatus() {
    if (Objects.isNull(currentStatus) && Objects.isNull(updatedAt)) {
      throw new BookingDomainException("Booking state is missing!");
    }
  }
}
