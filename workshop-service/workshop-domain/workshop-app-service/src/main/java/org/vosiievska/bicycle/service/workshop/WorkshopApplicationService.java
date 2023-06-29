package org.vosiievska.bicycle.service.workshop;

import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.workshop.dto.WorkshopApprovalRequest;
import org.vosiievska.bicycle.service.workshop.event.WorkshopApprovalResponseEvent;

public interface WorkshopApplicationService {

  WorkshopApprovalResponseEvent createBookingApprovedEvent(WorkshopApprovalRequest request);

  void updateWorkshopSpecialistStatus(BookingId request);
}
