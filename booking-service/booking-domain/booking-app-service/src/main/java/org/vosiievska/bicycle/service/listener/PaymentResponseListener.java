package org.vosiievska.bicycle.service.listener;

import org.vosiievska.bicycle.service.dto.response.PaymentResponse;

/**
 * Primary port according to the Hexagonal Architecture
 */
public interface PaymentResponseListener {

  void paidSuccessfully(PaymentResponse paymentResponse);

  void paymentFailed(PaymentResponse paymentResponse);

}
