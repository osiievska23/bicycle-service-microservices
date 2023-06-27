package org.vosiievska.bicycle.service.payment.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.entity.AggregateRoot;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;
import org.vosiievska.bicycle.service.payment.domain.valueobect.PaymentId;
import org.vosiievska.bicycle.service.domain.valueobject.PaymentStatus;
import org.vosiievska.bicycle.service.domain.valueobject.Price;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment extends AggregateRoot<PaymentId> {

  final BookingId bookingId;
  final ClientId clientId;
  final Price price;

  PaymentStatus currentStatus;
  LocalDateTime createdAt;

  public void initPayment() {
    this.setId(new PaymentId(UUID.randomUUID()));
    this.createdAt = LocalDateTime.now();
  }

  public void validatePaymentPrice(List<String> failureMessages) {
    if (Objects.isNull(price) || !price.isPositive()) {
      failureMessages.add("Payment price is invalid: " + price);
    }
  }

  public void updateCurrentStatus(PaymentStatus status) {
    this.currentStatus = status;
    this.createdAt = LocalDateTime.now();
  }
}
