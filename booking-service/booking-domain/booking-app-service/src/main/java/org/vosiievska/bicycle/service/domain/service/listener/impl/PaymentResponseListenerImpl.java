package org.vosiievska.bicycle.service.domain.service.listener.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vosiievska.bicycle.service.domain.service.dto.response.PaymentResponse;
import org.vosiievska.bicycle.service.domain.service.facade.BookingApplicationFacade;
import org.vosiievska.bicycle.service.domain.service.listener.PaymentResponseListener;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;

import java.util.UUID;

import static org.vosiievska.bicycle.service.domain.valueobject.BookingStatus.PAID;
import static org.vosiievska.bicycle.service.domain.valueobject.BookingStatus.PAYMENT_FAILED;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentResponseListenerImpl implements PaymentResponseListener {

  private final BookingApplicationFacade bookingApplicationFacade;

  @Override
  public void paidSuccessfully(PaymentResponse paymentResponse) {
    log.info("Processing successful payment for booking with id: {}", paymentResponse.getBookingId());
    BookingId bookingId = new BookingId(UUID.fromString(paymentResponse.getBookingId()));
    bookingApplicationFacade.updateBookingStatus(bookingId, PAID);
  }

  @Override
  public void paymentFailed(PaymentResponse paymentResponse) {
    log.info("Processing failed payment for booking with id: {} with failure messages: {}",
        paymentResponse.getBookingId(), String.join(",", paymentResponse.getFailureMessages()));
    BookingId bookingId = new BookingId(UUID.fromString(paymentResponse.getBookingId()));
    bookingApplicationFacade.updateBookingStatus(bookingId, PAYMENT_FAILED, paymentResponse.getFailureMessages());
  }
}
