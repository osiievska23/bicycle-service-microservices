package org.vosiievska.bicycle.service.domain.service.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopResponseStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkshopResponse {

  String id;
  String sagaId;
  UUID bookingId;
  UUID workshopId;
  UUID specialistId;
  String repairServiceId;
  WorkshopResponseStatus workshopResponseStatus;
  LocalDateTime createdAt;
  List<String> failureMessages;

}
