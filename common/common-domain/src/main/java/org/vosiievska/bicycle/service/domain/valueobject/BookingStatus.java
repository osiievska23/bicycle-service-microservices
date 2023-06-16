package org.vosiievska.bicycle.service.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Set;

@Getter
@AllArgsConstructor
public enum BookingStatus {

  PENDING, APPROVED, DECLINED, PAID, PAYMENT_FAILED, CANCELLING, CANCELLED, COMPLETE;

  private static final HashMap<BookingStatus, Set<BookingStatus>> allowedUpdateToStatuses = new HashMap<>();

  static {
    allowedUpdateToStatuses.put(PENDING, Set.of(APPROVED, DECLINED, CANCELLING));
    allowedUpdateToStatuses.put(APPROVED, Set.of(PAID, PAYMENT_FAILED, CANCELLING));
    allowedUpdateToStatuses.put(PAID, Set.of(COMPLETE));
    allowedUpdateToStatuses.put(PAYMENT_FAILED, Set.of(CANCELLING));
    allowedUpdateToStatuses.put(CANCELLING, Set.of(CANCELLED));
  }

  public boolean isNextStatusValid(BookingStatus nextStatus) {
    return allowedUpdateToStatuses.containsKey(nextStatus) && allowedUpdateToStatuses.get(this).contains(nextStatus);
  }
}
