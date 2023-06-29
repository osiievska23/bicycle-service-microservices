package org.vosiievska.bicycle.service.workshop.listener;

import org.vosiievska.bicycle.service.workshop.dto.WorkshopApprovalRequest;

public interface WorkshopApprovalRequestMessageListener {

  void approveBooking(WorkshopApprovalRequest request);

  void cancelBooking(WorkshopApprovalRequest request);
}
