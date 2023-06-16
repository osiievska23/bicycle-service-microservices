package org.vosiievska.bicycle.service.listener.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vosiievska.bicycle.service.dto.response.WorkshopResponse;
import org.vosiievska.bicycle.service.listener.WorkshopResponseListener;

@Slf4j
@Service
public class WorkshopResponseListenerImpl implements WorkshopResponseListener {
  @Override
  public void bookingApproved(WorkshopResponse workshopResponse) {
    log.info("Processing approved booking with id: {}", workshopResponse.getBookingId());
  }

  @Override
  public void bookingDeclined(WorkshopResponse workshopResponse) {
    log.info("Processing declined booking with id: {}, with failure messages: {}",
        workshopResponse.getBookingId(), String.join(",", workshopResponse.getFailureMessages()));
  }
}
