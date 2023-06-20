package org.vosiievska.bicycle.service.domain.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.vosiievska.bicycle.service.domain.service.dto.AddressDto;

import java.util.UUID;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateBookingRequest {

  @NotNull
  UUID clientId;
  @NotBlank
  String repairServiceId;
  @NotNull
  AddressDto clientAddress;

}
