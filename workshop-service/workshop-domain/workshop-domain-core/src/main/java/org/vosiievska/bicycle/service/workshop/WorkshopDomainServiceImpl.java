package org.vosiievska.bicycle.service.workshop;

import org.vosiievska.bicycle.service.workshop.entity.WorkshopApprovalResponse;

public class WorkshopDomainServiceImpl implements WorkshopDomainService {

  @Override
  public WorkshopApprovalResponse validateWorkshopApproval(WorkshopApprovalResponse workshopApproval) {
    workshopApproval.initialize();
    workshopApproval.validateWorkshopApproval();
    workshopApproval.setApprovalStatus();
    return workshopApproval;
  }
}
