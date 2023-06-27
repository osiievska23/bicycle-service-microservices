package org.vosiievska.bicycle.service.payment.dataaccess.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.vosiievska.bicycle.service.dataaccess.jpa.entity.PaymentEntity;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;
import org.vosiievska.bicycle.service.domain.valueobject.PaymentStatus;
import org.vosiievska.bicycle.service.domain.valueobject.Price;
import org.vosiievska.bicycle.service.payment.domain.entity.Payment;
import org.vosiievska.bicycle.service.payment.domain.valueobect.PaymentId;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    imports = {PaymentId.class, BookingId.class, ClientId.class, Price.class, PaymentStatus.class}
)
public interface PaymentJpaEntityMapper {

  @Mapping(target = "id", expression = "java(new PaymentId(jpaEntity.getId()))")
  @Mapping(target = "bookingId", expression = "java(new BookingId(jpaEntity.getBookingId()))")
  @Mapping(target = "clientId", expression = "java(new ClientId(jpaEntity.getClientId()))")
  @Mapping(target = "price", expression = "java(new Price(jpaEntity.getPrice()))")
  @Mapping(target = "currentStatus", expression = "java(PaymentStatus.valueOf(jpaEntity.getCurrentStatus()))")
  Payment jpaEntityToPayment(PaymentEntity jpaEntity);

  @Mapping(target = "id", expression = "java(payment.getId().getValue())")
  @Mapping(target = "bookingId", source = "payment.bookingId.value")
  @Mapping(target = "clientId", source = "payment.clientId.value")
  @Mapping(target = "price", source = "payment.price.amount")
  @Mapping(target = "currentStatus", expression = "java(payment.getCurrentStatus().name())")
  PaymentEntity paymentToJpaEntity(Payment payment);

}
