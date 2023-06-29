package org.vosiievska.bicycle.service.dataaccess.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.vosiievska.bicycle.service.dataaccess.interfaces.BookingStatusInterface;
import org.vosiievska.bicycle.service.dataaccess.jpa.entity.BookingEntity;
import org.vosiievska.bicycle.service.domain.core.entity.Booking;
import org.vosiievska.bicycle.service.domain.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
  @Mapping(target = "workshopId", ignore = true)
  @Mapping(target = "specialistId", ignore = true)
  @Mapping(target = "address", source = "clientAddress")
  @Mapping(target = "currentStatus", expression = "java(booking.getCurrentStatus().name())")
  @Mapping(target = "failureMessages",
      expression = "java(String.join(FAILURE_MESSAGES_SEPARATOR, booking.getFailureMessages()))")
  public abstract BookingEntity bookingToJpaEntity(Booking booking);

  @Mapping(target = "id", expression = "java(new BookingId(jpaEntity.getId()))")
  @Mapping(target = "clientId", expression = "java(new ClientId(jpaEntity.getClientId()))")
  @Mapping(target = "workshopId", ignore = true)
  @Mapping(target = "specialistId", ignore = true)
  @Mapping(target = "clientAddress", source = "address")
  @Mapping(target = "currentStatus", expression = "java(BookingStatus.valueOf(jpaEntity.getCurrentStatus()))")
  @Mapping(target = "failureMessages", expression = "java(mapFailureMessages(jpaEntity.getFailureMessages()))")
  public abstract Booking jpaEntityToBooking(BookingEntity jpaEntity);

  @AfterMapping
  void mapBookingEntity(@MappingTarget BookingEntity bookingEntity, Booking booking) {
    bookingEntity.setAddressId(bookingEntity.getAddress().getId());
    bookingEntity.setWorkshopId(booking.getWorkshopId() != null ? booking.getWorkshopId().getValue() : null);
    bookingEntity.setSpecialistId(booking.getSpecialistId() != null ? booking.getSpecialistId().getValue() : null);
  }

  @AfterMapping
  void mapBooking(@MappingTarget Booking booking, BookingEntity jpaEntity) {
    booking.setWorkshopId(jpaEntity.getWorkshopId() != null ? new WorkshopId(jpaEntity.getWorkshopId()) : null);
    booking.setSpecialistId(jpaEntity.getSpecialistId() != null ? new SpecialistId(jpaEntity.getSpecialistId()) : null);
  }

  public BookingStatusResponse jpaEntityToBookingStatus(BookingStatusInterface statusInterface) {
    return BookingStatusResponse.builder()
        .currentStatus(statusInterface.getCurrentStatus())
        .updatedAt(statusInterface.getUpdatedAt())
        .failureMessages(Arrays.asList(statusInterface.getFailureMessages().split(FAILURE_MESSAGES_SEPARATOR)))
        .build();
  }

  protected List<String> mapFailureMessages(String failureMessages) {
    return new ArrayList<>(Arrays.asList(failureMessages.split(FAILURE_MESSAGES_SEPARATOR)));
  }
}
