package org.vosiievska.bicycle.service.domain.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressDto {

  @NotBlank
  String city;
  @NotBlank
  String street;
  @NotBlank
  String houseNumber;
  @NotBlank
  String district;
  @NotBlank
  String zipCode;

}
