package org.vosiievska.bicycle.service.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Address {

  private final String city;
  private final String street;
  private final String houseNumber;
  private final String district;
  private final String zipCode;

}
