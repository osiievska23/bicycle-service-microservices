package org.vosiievska.bicycle.service.valueobject;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class UserPersonalInfo {

  private final String firstName;
  private final String lastName;
  private final LocalDate dob;
}
