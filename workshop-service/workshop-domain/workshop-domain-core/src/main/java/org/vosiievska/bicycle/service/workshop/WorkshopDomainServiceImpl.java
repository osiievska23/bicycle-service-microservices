package org.vosiievska.bicycle.service.workshop;

import org.vosiievska.bicycle.service.workshop.entity.WorkshopApprovalResponse;

import java.util.List;

import static org.vosiievska.bicycle.service.domain.valueobject.WorkshopResponseStatus.APPROVED;
import static org.vosiievska.bicycle.service.domain.valueobject.WorkshopResponseStatus.DECLINED;

public class WorkshopDomainServiceImpl implements WorkshopDomainService {

  @Override
  public WorkshopApprovalResponse validateWorkshopApproval(WorkshopApprovalResponse workshopApproval,
                                                           List<String> failureMessages) {
    workshopApproval.validateWorkshopApproval(failureMessages);
    workshopApproval.initialize();
    workshopApproval.setApprovalStatus(failureMessages.isEmpty() ? APPROVED : DECLINED);
    return workshopApproval;
  }
}
