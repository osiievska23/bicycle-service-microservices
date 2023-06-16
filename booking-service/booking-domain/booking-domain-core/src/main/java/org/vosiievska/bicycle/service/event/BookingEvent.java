package org.vosiievska.bicycle.service.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.event.DomainEvent;
import org.vosiievska.bicycle.service.entity.Booking;

import java.time.Instant;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BookingEvent implements DomainEvent {

  final Booking booking;
  final Instant createdAt;

  public BookingEvent(Booking booking, Instant createdAt) {
    this.booking = booking;
    this.createdAt = createdAt;
  }

  public BookingEvent(Booking booking) {
    this(booking, Instant.now());
  }
}
