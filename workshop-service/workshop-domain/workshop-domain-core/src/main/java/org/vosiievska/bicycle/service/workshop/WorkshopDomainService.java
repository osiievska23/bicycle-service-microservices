package org.vosiievska.bicycle.service.workshop;

import org.vosiievska.bicycle.service.workshop.entity.WorkshopApprovalResponse;

import java.util.List;

public interface WorkshopDomainService {

  WorkshopApprovalResponse validateWorkshopApproval(WorkshopApprovalResponse workshop, List<String> failureMessages);
}
