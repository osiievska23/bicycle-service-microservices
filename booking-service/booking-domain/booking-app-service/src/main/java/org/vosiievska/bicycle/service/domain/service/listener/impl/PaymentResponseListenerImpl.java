package org.vosiievska.bicycle.service.domain.service.listener.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vosiievska.bicycle.service.domain.service.dto.response.PaymentResponse;
import org.vosiievska.bicycle.service.domain.service.listener.PaymentResponseListener;

@Slf4j
@Service
public class PaymentResponseListenerImpl implements PaymentResponseListener {
  @Override
  public void paidSuccessfully(PaymentResponse paymentResponse) {
    log.info("Processing successful payment for booking with id: {}", paymentResponse.getBookingId());

  }

  @Override
  public void paymentFailed(PaymentResponse paymentResponse) {
    log.info("Processing failed payment for booking with id: {} with failure messages: {}",
        paymentResponse.getBookingId(), String.join(",", paymentResponse.getFailureMessages()));

  }
}
