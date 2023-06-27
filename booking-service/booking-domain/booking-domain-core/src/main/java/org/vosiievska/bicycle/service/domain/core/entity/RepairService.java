package org.vosiievska.bicycle.service.domain.core.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.core.exception.BookingDomainException;
import org.vosiievska.bicycle.service.domain.entity.BaseEntity;
import org.vosiievska.bicycle.service.domain.valueobject.Price;
import org.vosiievska.bicycle.service.domain.valueobject.RepairServiceId;

/**
 * Class represents maintenance or repair services for bicycles.
 */
@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RepairService extends BaseEntity<RepairServiceId> {

  final String title;
  final Price price;

  public void validatePrice() {
    if (!price.isPositive()) {
      throw new BookingDomainException("Repair service price [%s] is not positive", price);
    }
  }
}
