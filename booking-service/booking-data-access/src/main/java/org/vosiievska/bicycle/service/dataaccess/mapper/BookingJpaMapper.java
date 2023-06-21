package org.vosiievska.bicycle.service.dataaccess.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.vosiievska.bicycle.service.dataaccess.entity.BookingEntity;
import org.vosiievska.bicycle.service.dataaccess.interfaces.BookingStatusInterface;
import org.vosiievska.bicycle.service.domain.core.entity.Booking;
import org.vosiievska.bicycle.service.domain.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;

import java.util.Arrays;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {AddressJpaMapper.class, BookingStatus.class, RepairServiceJpaMapper.class},
    imports = {BookingId.class, ClientId.class, WorkshopId.class, SpecialistId.class, BookingStatus.class, Arrays.class}
)
public abstract class BookingJpaMapper {

  public static final String FAILURE_MESSAGES_SEPARATOR = ";;";

  @Mapping(target = "id", expression = "java(booking.getIdValue())")
  @Mapping(target = "clientId", expression = "java(booking.getClientId().getValue())")
  @Mapping(target = "workshopId", expression = "java(booking.getWorkshopId().getValue())")
  @Mapping(target = "specialistId", expression = "java(booking.getSpecialistId().getValue())")
  @Mapping(target = "address", source = "clientAddress")
  @Mapping(target = "currentStatus", expression = "java(booking.getCurrentStatus().name())")
  @Mapping(target = "failureMessages", expression = "java(String.join(FAILURE_MESSAGES_SEPARATOR, booking.getFailureMessages()))")
  public abstract BookingEntity bookingToJpaEntity(Booking booking);

  @Mapping(target = "id", expression = "java(new BookingId(jpaEntity.getId()))")
  @Mapping(target = "clientId", expression = "java(new ClientId(jpaEntity.getClientId()))")
  @Mapping(target = "workshopId", expression = "java(new WorkshopId(jpaEntity.getWorkshopId()))")
  @Mapping(target = "specialistId", expression = "java(new SpecialistId(jpaEntity.getSpecialistId()))")
  @Mapping(target = "clientAddress", source = "address")
  @Mapping(target = "currentStatus", expression = "java(BookingStatus.valueOf(jpaEntity.getCurrentStatus()))")
  @Mapping(target = "failureMessages", expression = "java(Arrays.asList(jpaEntity.getFailureMessages().split(FAILURE_MESSAGES_SEPARATOR)))")
  public abstract Booking jpaEntityToBooking(BookingEntity jpaEntity);

  @AfterMapping
  void mapWorkshopAddress(@MappingTarget BookingEntity bookingEntity) {
    bookingEntity.setAddressId(bookingEntity.getAddress().getId());
  }

  public BookingStatusResponse jpaEntityToBookingStatus(BookingStatusInterface statusInterface) {
    return BookingStatusResponse.builder()
        .currentStatus(statusInterface.getCurrentStatus())
        .updatedAt(statusInterface.getUpdatedAt())
        .failureMessages(Arrays.asList(statusInterface.getFailureMessages().split(FAILURE_MESSAGES_SEPARATOR)))
        .build();
  }

//  @AfterMapping
//  void jpaEntityToBooking(@MappingTarget Booking booking, BookingEntity bookingEntity) {
//    booking.setId(new BookingId(bookingEntity.getId()));
//  }
}
