package org.vosiievska.bicycle.service.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.entity.AggregateRoot;
import org.vosiievska.bicycle.service.domain.valueobject.Address;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;
import org.vosiievska.bicycle.service.domain.valueobject.CustomerId;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;
import org.vosiievska.bicycle.service.exception.BookingDomainException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking extends AggregateRoot<BookingId> {

  final CustomerId customerId;
  final WorkshopId workshopId;
  final SpecialistId specialistId;
  final RepairService repairService;
  final Address clientAddress;
//  final Price price;

  // business logic updatable fields
//  List<BookingTracking> tracking;//todo: delete
  BookingStatus currentStatus;
  Instant updatedAt;
  List<String> failureMessages;

  public UUID getIdValue() {
    return getId().getValue();
  }

  public void initBooking() {
    super.setId(new BookingId(UUID.randomUUID()));
    this.failureMessages = new ArrayList<>();
//    this.tracking = new ArrayList<>();
    updateCurrentStatus(BookingStatus.PENDING);
  }
  public void updateCurrentStatus(BookingStatus status) {
    validateNextBookingStatus(status);
//    this.tracking.add(new BookingTracking(status));//todo: delete
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
    if (!currentStatus.isNextStatusValid(newStatus)) {
      throw new BookingDomainException(
          "Updating booking status from '%s' to '%s' is not allowed!", this.currentStatus.name(), newStatus.name());
    }
  }

  public void validateBooking() {
    validateBookingStatus();
//    validateBookingTracking(); //todo: delete
    repairService.validatePrice();
  }

  private void validateBookingStatus() {
    if (Objects.isNull(currentStatus) && Objects.isNull(updatedAt)) {
      throw new BookingDomainException("Booking state is missing!");
    }
  }

//  private void validateBookingTracking() {
//    if (tracking.isEmpty() || !currentStatus.equals(tracking.get(tracking.size() - 1).getStatus())) {
//      throw new BookingDomainException("Tracking problem detected!");
//    }
//  }

//  private void validateBookingPrice() {
//    if (!repairService.getPrice().isPositive()) {
////    if (!price.isPositive() && !price.equals(repairService.getPrice())) {
//      throw new BookingDomainException(
//          "Repai price [%s] does not match the service cost [%s]!", price, repairService.getPrice());
//    }
//  }
}
