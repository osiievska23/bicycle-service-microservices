package org.vosiievska.payment.service.messaging.mapper;

import com.bicycle.service.avro.payment.AvroPaymentRequest;
import com.bicycle.service.avro.payment.AvroPaymentResponse;
import com.bicycle.service.avro.payment.PaymentStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.payment.domain.event.PaymentEvent;
import org.vosiievska.bicycle.service.payment.domain.service.dto.PaymentRequest;

import java.util.List;

@Mapper(
    componentModel = "spring",
    imports = {PaymentStatus.class}
)
public interface PaymentAvroMessagingMapper {

  @Mapping(target = "id", expression = "java(UUID.randomUUID().toString())")
  @Mapping(target = "bookingId", source = "event.domain.bookingId.value")
  @Mapping(target = "clientId", source = "event.domain.clientId.value")
  @Mapping(target = "paymentId", source = "event.domain.id.value")
  @Mapping(target = "price", source = "event.domain.price.amount")
  @Mapping(target = "paymentStatus",
      expression = "java(PaymentStatus.valueOf(event.getDomain().getCurrentStatus().name()))")
  AvroPaymentResponse paymentEventToAvroPaymentResponse(PaymentEvent event);

  PaymentRequest avroPaymentRequestToPaymentRequest(AvroPaymentRequest avroPaymentRequest);

  List<PaymentRequest> avroPaymentRequestToPaymentRequest(List<AvroPaymentRequest> avroPaymentRequest);

}
