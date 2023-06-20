package org.vosiievska.bicycle.service.messaging.mapper;

import com.bicycle.service.avro.payment.AvroPaymentRequest;
import com.bicycle.service.avro.payment.AvroPaymentResponse;
import com.bicycle.service.avro.payment.AvroWorkshopApprovalRequest;
import com.bicycle.service.avro.payment.AvroWorkshopApprovalResponse;
import com.bicycle.service.avro.payment.BookingStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.domain.core.event.BookingEvent;
import org.vosiievska.bicycle.service.domain.service.dto.response.PaymentResponse;
import org.vosiievska.bicycle.service.domain.service.dto.response.WorkshopResponse;
import org.vosiievska.bicycle.service.domain.valueobject.PaymentStatus;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopResponseStatus;

import java.util.List;

@Mapper(
    componentModel = "spring",
    imports = {PaymentStatus.class, WorkshopResponseStatus.class, BookingStatus.class}
)
public interface BookingAvroMessagingMapper {

  @Mapping(target = "id", expression = "java(UUID.randomUUID().toString())")
  @Mapping(target = "clientId", source = "event.booking.specialistId.value")
  @Mapping(target = "bookingId", source = "event.booking.id.value")
  @Mapping(target = "price", source = "event.booking.repairService.price.amount")
  @Mapping(target = "bookingStatus",
      expression = "java(BookingStatus.valueOf(event.getBooking().getCurrentStatus().name()))")
  AvroPaymentRequest bookingEventToAvroPaymentRequest(BookingEvent event);

  @Mapping(target = "id", expression = "java(UUID.randomUUID().toString())")
  @Mapping(target = "workshopId", source = "event.booking.workshopId.value")
  @Mapping(target = "bookingId", source = "event.booking.id.value")
  @Mapping(target = "repairServiceId", source = "event.booking.repairService.title")
  @Mapping(target = "bookingStatus",
      expression = "java(BookingStatus.valueOf(event.getBooking().getCurrentStatus().name()))")
  AvroWorkshopApprovalRequest bookingEventToAvroWorkshopApprovalRequest(BookingEvent event);

  @Mapping(target = "paymentStatus", expression = "java(PaymentStatus.valueOf(avroResponse.getPaymentStatus().name()))")
  PaymentResponse avroPaymentResponseToPaymentRequest(AvroPaymentResponse avroResponse);

  List<PaymentResponse> avroPaymentResponseToPaymentRequestList(List<AvroPaymentResponse> avroResponse);

  @Mapping(target = "workshopResponseStatus",
      expression = "java(WorkshopResponseStatus.valueOf(avroResponse.getWorkshopResponseStatus().name()))")
  WorkshopResponse avroWorkshopResponseToPaymentRequest(AvroWorkshopApprovalResponse avroResponse);

  List<WorkshopResponse> avroWorkshopResponseToPaymentRequestList(List<AvroWorkshopApprovalResponse> avroMessages);
}
