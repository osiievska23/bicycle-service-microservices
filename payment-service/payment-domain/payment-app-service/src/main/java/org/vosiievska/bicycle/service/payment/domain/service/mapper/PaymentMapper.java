package org.vosiievska.bicycle.service.payment.domain.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;
import org.vosiievska.bicycle.service.domain.valueobject.Price;
import org.vosiievska.bicycle.service.payment.domain.entity.Payment;
import org.vosiievska.bicycle.service.payment.domain.service.dto.PaymentRequest;
import org.vosiievska.bicycle.service.payment.domain.valueobect.PaymentId;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = IGNORE,
    imports = {PaymentId.class, BookingId.class, ClientId.class, Price.class}
)
public interface PaymentMapper {

  @Mapping(target = "id", expression = "java(new PaymentId(request.getId()))")
  @Mapping(target = "bookingId", expression = "java(new BookingId(request.getBookingId()))")
  @Mapping(target = "clientId", expression = "java(new ClientId(request.getClientId()))")
  @Mapping(target = "price", expression = "java(new Price(request.getPrice()))")
  Payment paymentRequestToPaymentEntity(PaymentRequest request);

}
