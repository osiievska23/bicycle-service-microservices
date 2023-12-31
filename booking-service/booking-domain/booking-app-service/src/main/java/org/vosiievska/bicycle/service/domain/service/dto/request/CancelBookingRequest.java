package org.vosiievska.bicycle.service.domain.service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CancelBookingRequest {

  @NotNull
  UUID bookingId;
  @NotNull
  UUID clientId;
}
