package org.vosiievska.bicycle.service.payment.domain.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequest {

  UUID id;
  UUID sagaId;
  UUID bookingId;
  UUID clientId;
  BigDecimal price;
  LocalDateTime createdAt;
  BookingStatus bookingStatus;
}
