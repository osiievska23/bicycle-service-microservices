package org.vosiievska.bicycle.service.domain.service.listener.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vosiievska.bicycle.service.domain.service.dto.response.WorkshopResponse;
import org.vosiievska.bicycle.service.domain.service.facade.BookingApplicationFacade;
import org.vosiievska.bicycle.service.domain.service.listener.WorkshopResponseListener;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;

import static org.vosiievska.bicycle.service.domain.valueobject.BookingStatus.DECLINED;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkshopResponseListenerImpl implements WorkshopResponseListener {

  private final BookingApplicationFacade bookingApplicationFacade;

  @Override
  public void bookingApproved(WorkshopResponse workshopResponse) {
    log.info("Processing approved booking with id: {}", workshopResponse.getBookingId());
    bookingApplicationFacade.payBooking(workshopResponse);
  }

  @Override
  public void bookingDeclined(WorkshopResponse workshopResponse) {
    log.info("Processing declined booking with id: {}, with failure messages: {}",
        workshopResponse.getBookingId(), String.join(",", workshopResponse.getFailureMessages()));
    BookingId bookingId = new BookingId(workshopResponse.getBookingId());
    bookingApplicationFacade.updateBookingStatus(bookingId, DECLINED, workshopResponse.getFailureMessages());
  }
}
