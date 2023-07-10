package org.vosiievska.bicycle.service.domain.service.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingStatusResponse {

  @NotNull
  BookingStatus currentStatus;
  @NotNull
  Instant updatedAt;
  List<String> failureMessages;

  public BookingStatusResponse(String failureMessage) {
    this.failureMessages = new ArrayList<>();
    this.failureMessages.add(failureMessage);
  }

  public BookingStatusResponse(String failureMessage, Object... args) {
    this(String.format(failureMessage, args));
  }
}
