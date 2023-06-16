package org.vosiievska.bicycle.service.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;

import java.time.Instant;
import java.util.List;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingStatusResponse {

  @NotNull
  BookingStatus currentStatus;
  @NotNull
  Instant updatedAt;
  List<String> failureMessages;
}
