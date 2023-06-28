package org.vosiievska.bicycle.service.workshop.event;

import org.vosiievska.bicycle.service.workshop.entity.WorkshopApprovalResponse;

public class BookingApprovedEvent extends WorkshopApprovalResponseEvent {

  public BookingApprovedEvent(WorkshopApprovalResponse domain) {
    super(domain);
  }
}
