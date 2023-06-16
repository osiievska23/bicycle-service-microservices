package org.vosiievska.bicycle.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.dto.request.CreateBookingRequest;
import org.vosiievska.bicycle.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.entity.Booking;
import org.vosiievska.bicycle.service.entity.RepairService;
import org.vosiievska.bicycle.service.entity.Specialist;
import org.vosiievska.bicycle.service.entity.Workshop;
import org.vosiievska.bicycle.service.exception.BookingDomainException;

import java.util.Set;

@Mapper(
    componentModel = "spring",
    uses = AddressMapper.class)
public interface BookingMapper {

  @Mapping(target = "clientId", expression = "java(new ClientId(request.getClientId()))")
  @Mapping(target = "workshopId", source = "workshop.id")
  @Mapping(target = "specialistId", expression = "java(getAvailableSpecialist(workshop.getSpecialists()))")
  @Mapping(target = "repairService", source = "repairService")
  @Mapping(target = "clientAddress", source = "clientAddress")
  Booking createBookingRequestToEntity(CreateBookingRequest request, Workshop workshop, RepairService repairService);

  @Mapping(target = "status", expression = "java(booking.getCurrentStatus().name())")
  @Mapping(target = "updatedAt", source = "booking.updatedAt")
  BookingStatusResponse bookingToBookingStatusResponse(Booking booking);

  private SpecialistId getAvailableSpecialist(Set<Specialist> specialists) {
    return specialists.stream().filter(s -> !s.isBusy()).findFirst()
        .orElseThrow(() -> new BookingDomainException("Available specialist not found"))
        .getId();
  }
}
