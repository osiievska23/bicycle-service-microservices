package org.vosiievska.bicycle.service.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

/**
 * Entity should have an identity. It's recommended to represent the entity identity as a Value Object what is
 * consistent and easily validated.
 * @param <T> identity field type
 */
@Getter
@EqualsAndHashCode
public abstract class BaseId<T> {

  private final T value;

  protected BaseId(T value) {
    this.value = value;
    validateBaseId();
  }

  protected void validateBaseId() {
    if (Objects.isNull(this.value)) {
      throw new IllegalArgumentException("Entity identity could not be null!");
    }
    if (this.value instanceof String && this.value.toString().strip().isBlank()) {
      throw new IllegalArgumentException("Entity identity could not be empty!");
    }
  }
}
