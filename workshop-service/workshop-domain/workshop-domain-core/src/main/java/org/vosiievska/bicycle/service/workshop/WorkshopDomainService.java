package org.vosiievska.bicycle.service.workshop;

import org.vosiievska.bicycle.service.workshop.entity.WorkshopApprovalResponse;

public interface WorkshopDomainService {

  WorkshopApprovalResponse validateWorkshopApproval(WorkshopApprovalResponse workshop);
}
