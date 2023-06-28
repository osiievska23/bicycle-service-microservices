package org.vosiievska.bicycle.service.workshop.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkshopApprovalRequest {

  UUID id;
  UUID sagaId;
  UUID workshopId;
  UUID bookingId;
  UUID clientId;
  String repairServiceId;
  LocalDateTime createdAt;
  BookingStatus bookingStatus;
}
