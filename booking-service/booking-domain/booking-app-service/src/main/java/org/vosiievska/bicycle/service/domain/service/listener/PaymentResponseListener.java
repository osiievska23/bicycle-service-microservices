package org.vosiievska.bicycle.service.domain.service.listener;

import org.vosiievska.bicycle.service.domain.service.dto.response.PaymentResponse;

/**
 * Primary port according to the Hexagonal Architecture
 */
public interface PaymentResponseListener {

  void paidSuccessfully(PaymentResponse paymentResponse);

  void paymentFailed(PaymentResponse paymentResponse);

}
