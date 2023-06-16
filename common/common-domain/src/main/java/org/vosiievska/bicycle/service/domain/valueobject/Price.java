package org.vosiievska.bicycle.service.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Price {

  private final BigDecimal amount;

  public boolean isPositive() {
    return this.amount.compareTo(BigDecimal.ZERO) > 0;
  }

  public boolean isGreaterThen(Price price) {
    return Objects.nonNull(price) && this.amount.compareTo(price.getAmount()) > 0;
  }

  public Price add(Price price) {
    validatePrice(price);
    return new Price(setScale(this.amount.add(price.getAmount())));
  }

  public Price subtract(Price price) {
    validatePrice(price);
    return new Price(setScale(this.amount.subtract(price.getAmount())));
  }

  public Price multiply(Price price) {
    validatePrice(price);
    return new Price(setScale(this.amount.multiply(price.getAmount())));
  }

  public Price divide(Price price) {
    validatePrice(price);
    return new Price(setScale(this.amount.divide(price.getAmount())));
  }

  private BigDecimal setScale(BigDecimal value) {
    return value.setScale(2, RoundingMode.HALF_EVEN);
  }

  private void validatePrice(Price price) {
    if (Objects.isNull(price) || !isPositive()) {
      throw new NullPointerException("Price object is invalid!");
    }
  }
}
