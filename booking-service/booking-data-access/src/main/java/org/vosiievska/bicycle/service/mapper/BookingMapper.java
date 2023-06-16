package org.vosiievska.bicycle.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;
import org.vosiievska.bicycle.service.dto.interfaces.BookingStatusInterface;
import org.vosiievska.bicycle.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.entity.Booking;
import org.vosiievska.bicycle.service.entity.BookingEntity;

import java.util.Arrays;

@Mapper(
    componentModel = "spring",
    uses = {AddressMapper.class, BookingStatus.class})
public abstract class BookingMapper {

  private static final String FAILURE_MESSAGES_SEPARATOR = ";;";

  @Mapping(target = "id", expression = "java(booking.getIdValue())")
  @Mapping(target = "clientId", expression = "java(booking.getClientId().getValue())")
  @Mapping(target = "workshopId", expression = "java(booking.getWorkshopId().getValue())")
  @Mapping(target = "specialistId", expression = "java(booking.getSpecialistId().getValue())")
  @Mapping(target = "currentStatus", expression = "java(booking.getCurrentStatus().name())")
  @Mapping(target = "failureMessages", expression = "java(String.join(FAILURE_MESSAGES_SEPARATOR, booking.getFailureMessages()))")
  public abstract BookingEntity bookingToJpaEntity(Booking booking);

  @Mapping(target = "id", expression = "java(new BookingId(jpaEntity.getId()))")
  @Mapping(target = "clientId", expression = "java(new ClientId(jpaEntity.getClientId()))")
  @Mapping(target = "workshopId", expression = "java(new WorkshopId(jpaEntity.getWorkshopId()))")
  @Mapping(target = "specialist", expression = "java(new SpecialistId(jpaEntity.getSpecialistId()))")
  @Mapping(target = "currentStatus", expression = "java(BookingStatus.valueOf(jpaEntity.getCurrentStatus()))")
  @Mapping(target = "failureMessages", expression = "java(Arrays.asList(jpaEntity.getFailureMessages().split(FAILURE_MESSAGES_SEPARATOR)))")
  public abstract Booking jpaEntityToBooking(BookingEntity jpaEntity);

  public BookingStatusResponse jpaEntityToBookingStatus(BookingStatusInterface statusInterface) {
    return BookingStatusResponse.builder()
        .currentStatus(statusInterface.getCurrentStatus())
        .updatedAt(statusInterface.getUpdatedAt())
        .failureMessages(Arrays.asList(statusInterface.getFailureMessages().split(FAILURE_MESSAGES_SEPARATOR)))
        .build();
  }

}
