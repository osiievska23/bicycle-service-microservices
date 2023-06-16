package org.vosiievska.bicycle.service.listener.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vosiievska.bicycle.service.dto.response.PaymentResponse;
import org.vosiievska.bicycle.service.listener.PaymentResponseListener;

@Slf4j
@Service
public class PaymentResponseListenerImpl implements PaymentResponseListener {
  @Override
  public void paidSuccessfully(PaymentResponse paymentResponse) {

  }

  @Override
  public void paymentFailed(PaymentResponse paymentResponse) {

  }
}
