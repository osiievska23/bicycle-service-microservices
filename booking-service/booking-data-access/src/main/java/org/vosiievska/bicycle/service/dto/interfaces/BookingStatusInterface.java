package org.vosiievska.bicycle.service.dto.interfaces;

import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;

import java.time.Instant;

public interface BookingStatusInterface {

  BookingStatus getCurrentStatus();
  Instant getUpdatedAt();
  String getFailureMessages();
}
