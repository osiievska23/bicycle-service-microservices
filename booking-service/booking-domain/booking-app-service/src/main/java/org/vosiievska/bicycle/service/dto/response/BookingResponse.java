package org.vosiievska.bicycle.service.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;

import java.util.UUID;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {

  @NotNull
  UUID bookingTrackingId;
  @NotNull
  BookingStatus status;
  @NotBlank
  String message;

}
