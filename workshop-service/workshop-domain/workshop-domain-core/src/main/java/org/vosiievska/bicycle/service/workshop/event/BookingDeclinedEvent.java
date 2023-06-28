package org.vosiievska.bicycle.service.workshop.event;

import org.vosiievska.bicycle.service.workshop.entity.WorkshopApprovalResponse;

import java.util.List;

public class BookingDeclinedEvent extends WorkshopApprovalResponseEvent {

  public BookingDeclinedEvent(WorkshopApprovalResponse domain, List<String> failureMessages) {
    super(domain, failureMessages);
  }
}
