package org.vosiievska.bicycle.service.domain.service.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.valueobject.PaymentStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {

  String id;
  String sagaId;
  String bookingId;
  String clientId;
  String paymentId;
  String price;
  LocalDateTime createdAt;
  PaymentStatus paymentStatus;
  List<String> failureMessages;

}
