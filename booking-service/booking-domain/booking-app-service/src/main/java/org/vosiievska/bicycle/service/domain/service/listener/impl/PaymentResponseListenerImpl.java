package org.vosiievska.bicycle.service.domain.service.listener.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vosiievska.bicycle.service.domain.core.event.BookingEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingPaidEvent;
import org.vosiievska.bicycle.service.domain.event.DomainEventPublisher;
import org.vosiievska.bicycle.service.domain.service.dto.response.PaymentResponse;
import org.vosiievska.bicycle.service.domain.service.exception.BookingEventPublisherException;
import org.vosiievska.bicycle.service.domain.service.facade.BookingApplicationFacade;
import org.vosiievska.bicycle.service.domain.service.listener.PaymentResponseListener;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentResponseListenerImpl implements PaymentResponseListener {

  private final BookingApplicationFacade bookingApplicationFacade;

  @Override
  public void paidSuccessfully(PaymentResponse paymentResponse) {
    log.info("Processing successful payment for booking with id: {}", paymentResponse.getBookingId());
    bookingApplicationFacade.approveBookingByWorkshop(paymentResponse);
  }

  @Override
  public void paymentCancelled(PaymentResponse paymentResponse) {
    log.info("Processing cancelled payment for booking with id: {}", paymentResponse.getBookingId());
  }

  @Override
  public void paymentFailed(PaymentResponse paymentResponse) {
    log.info("Processing failed payment for booking with id: {} with failure messages: {}",
        paymentResponse.getBookingId(), String.join(",", paymentResponse.getFailureMessages()));

  }
}
