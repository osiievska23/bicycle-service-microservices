package org.vosiievska.bicycle.service.workshop;

import org.vosiievska.bicycle.service.workshop.dto.WorkshopApprovalRequest;
import org.vosiievska.bicycle.service.workshop.event.WorkshopApprovalResponseEvent;

public interface WorkshopApplicationService {
  WorkshopApprovalResponseEvent createWorkshopApproval(WorkshopApprovalRequest request);
}
