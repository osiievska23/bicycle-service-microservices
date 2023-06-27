package org.vosiievska.bicycle.service.payment.domain.service.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vosiievska.bicycle.service.domain.event.DomainEventPublisher;
import org.vosiievska.bicycle.service.payment.domain.event.PaymentEvent;
import org.vosiievska.bicycle.service.payment.domain.service.dto.PaymentRequest;
import org.vosiievska.bicycle.service.payment.domain.service.exception.PaymentApplicationServiceException;
import org.vosiievska.bicycle.service.payment.domain.service.service.PaymentApplicationService;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {

  private final PaymentApplicationService paymentApplicationService;
  private final Set<DomainEventPublisher<PaymentEvent>> domainEventPublisher;

  @Override
  public void completePayment(PaymentRequest paymentRequest) {
    log.info("Payment completing for booking with id: {}", paymentRequest.getBookingId());
    publishPaymentResponseEvent(paymentApplicationService.createCompletedPayment(paymentRequest));
  }

  @Override
  public void cancelPayment(PaymentRequest paymentRequest) {
    log.info("Payment cancelling for booking with id: {}", paymentRequest.getBookingId());
    publishPaymentResponseEvent(paymentApplicationService.createCancelPayment(paymentRequest));
  }

  private void publishPaymentResponseEvent(PaymentEvent event) {
    domainEventPublisher.stream()
        .filter(publisher -> publisher.supports(event))
        .findFirst()
        .orElseThrow(() -> new PaymentApplicationServiceException(
            "Payment event publisher supporting %s event not found", event.getClass().getSimpleName()))
        .publish(event);
  }
}
