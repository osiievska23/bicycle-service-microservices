package org.vosiievska.bicycle.service.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.entity.Entity;
import org.vosiievska.bicycle.service.domain.valueobject.Price;
import org.vosiievska.bicycle.service.domain.valueobject.RepairServiceId;
import org.vosiievska.bicycle.service.exception.BookingDomainException;

/**
 * Class represents maintenance or repair services for bicycles.
 */
@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RepairService extends Entity<RepairServiceId> {

  final String title;
  final Price price;

  public void validatePrice() {
    if (!price.isPositive()) {
      throw new BookingDomainException("Repair service price [%s] is not positive", price);
    }
  }
}
