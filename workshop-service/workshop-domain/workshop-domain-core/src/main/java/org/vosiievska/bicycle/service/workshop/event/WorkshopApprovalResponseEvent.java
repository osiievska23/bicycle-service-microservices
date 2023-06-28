package org.vosiievska.bicycle.service.workshop.event;

import org.vosiievska.bicycle.service.domain.event.AbstractDomainEvent;
import org.vosiievska.bicycle.service.workshop.entity.WorkshopApprovalResponse;

import java.time.LocalDateTime;
import java.util.List;

public abstract class WorkshopApprovalResponseEvent extends AbstractDomainEvent<WorkshopApprovalResponse> {

  public WorkshopApprovalResponseEvent(WorkshopApprovalResponse domain, LocalDateTime createdAt,
                                       List<String> failureMessages) {
    super(domain, createdAt, failureMessages);
  }

  public WorkshopApprovalResponseEvent(WorkshopApprovalResponse domain, List<String> failureMessages) {
    super(domain, failureMessages);
  }

  public WorkshopApprovalResponseEvent(WorkshopApprovalResponse domain) {
    super(domain);
  }
}
