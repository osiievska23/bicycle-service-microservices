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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.vosiievska.bicycle.service.domain.valueobject.WorkshopResponseStatus.APPROVED;
import static org.vosiievska.bicycle.service.domain.valueobject.WorkshopResponseStatus.DECLINED;

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
  List<String> failureMessages;

  public void initialize() {
    this.setId(new BookingApprovalId(UUID.randomUUID()));
    this.createdAt = LocalDateTime.now();
    this.setFailureMessages(new ArrayList<>());
  }


  public void validateWorkshopApproval() {
    this.workshop.validateWorkshop();
    this.failureMessages.addAll(this.workshop.getFailureMessages());
  }

  public void setApprovalStatus() {
    this.approvalStatus = this.failureMessages.isEmpty() ? APPROVED : DECLINED;
  }
}
