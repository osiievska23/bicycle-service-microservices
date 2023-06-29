package org.vosiievska.bicycle.service.workshop.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.entity.BaseEntity;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.RepairServiceId;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopResponseStatus;
import org.vosiievska.bicycle.service.workshop.valueobject.BookingApprovalId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkshopApprovalResponse extends BaseEntity<BookingApprovalId> {

  final BookingId bookingId;
  final RepairServiceId repairServiceId;

  Workshop workshop;
  SpecialistId specialistId;
  LocalDateTime createdAt;
  WorkshopResponseStatus approvalStatus;

  public void initialize() {
    this.setId(new BookingApprovalId(UUID.randomUUID()));
    this.createdAt = LocalDateTime.now();
  }


  public void validateWorkshopApproval(List<String> failureMessages) {
    this.workshop.validateWorkshop(failureMessages);
  }
}
