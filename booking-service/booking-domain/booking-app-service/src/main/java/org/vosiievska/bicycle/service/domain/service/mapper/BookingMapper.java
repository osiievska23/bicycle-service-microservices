package org.vosiievska.bicycle.service.domain.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.vosiievska.bicycle.service.domain.core.entity.Booking;
import org.vosiievska.bicycle.service.domain.core.entity.RepairService;
import org.vosiievska.bicycle.service.domain.core.entity.Specialist;
import org.vosiievska.bicycle.service.domain.core.entity.Workshop;
import org.vosiievska.bicycle.service.domain.exception.EntityNotFoundException;
import org.vosiievska.bicycle.service.domain.service.dto.request.CreateBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;

import java.util.Set;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = AddressMapper.class,
    imports = ClientId.class
)
public interface BookingMapper {

  @Mapping(target = "clientId", expression = "java(new ClientId(request.getClientId()))")
  @Mapping(target = "workshopId", source = "workshop.id")
  @Mapping(target = "specialistId", expression = "java(getAvailableSpecialist(workshop.getSpecialists()))")
  @Mapping(target = "repairService", source = "repairService")
  @Mapping(target = "clientAddress", source = "request.clientAddress")
  Booking createBookingRequestToEntity(CreateBookingRequest request, Workshop workshop, RepairService repairService);

  BookingStatusResponse bookingToBookingStatusResponse(Booking booking);

  default SpecialistId getAvailableSpecialist(Set<Specialist> specialists) {
    return specialists.stream().filter(s -> !s.isBusy()).findFirst()
        .orElseThrow(() -> new EntityNotFoundException("Available specialist not found"))
        .getId();
  }
}
