package org.vosiievska.bicycle.service.domain.core.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.core.valueobject.BookingTrackingId;
import org.vosiievska.bicycle.service.domain.entity.Entity;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;

import java.time.Instant;
import java.util.UUID;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingTracking extends Entity<BookingTrackingId> { //todo: delete

  final BookingStatus status;
  final Instant updatedAt;

  public BookingTracking(BookingTrackingId id, BookingStatus status, Instant updatedAt) {
    super.setId(id);
    this.status = status;
    this.updatedAt = updatedAt;
  }

  public BookingTracking(BookingStatus status, Instant updatedAt) {
    this(new BookingTrackingId(UUID.randomUUID()), status, updatedAt);
  }

  public BookingTracking(BookingStatus status) {
    this(status, Instant.now());
  }
}
