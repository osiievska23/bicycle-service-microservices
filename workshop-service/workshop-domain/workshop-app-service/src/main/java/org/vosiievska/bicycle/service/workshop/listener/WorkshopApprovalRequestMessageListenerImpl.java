package org.vosiievska.bicycle.service.workshop.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vosiievska.bicycle.service.domain.event.DomainEventPublisher;
import org.vosiievska.bicycle.service.workshop.WorkshopApplicationService;
import org.vosiievska.bicycle.service.workshop.dto.WorkshopApprovalRequest;
import org.vosiievska.bicycle.service.workshop.event.WorkshopApprovalResponseEvent;
import org.vosiievska.bicycle.service.workshop.exception.WorkshopApplicationServiceException;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkshopApprovalRequestMessageListenerImpl implements WorkshopApprovalRequestMessageListener {

  private final WorkshopApplicationService workshopApplicationService;
  private final Set<DomainEventPublisher<? extends WorkshopApprovalResponseEvent>> domainEventPublisher;

  @Override
  public void approveBooking(WorkshopApprovalRequest request) {
    log.info("Workshop approval processing for booking with id: {}", request.getBookingId());
    publishResponseEvent(workshopApplicationService.createWorkshopApproval(request));
  }

  @SuppressWarnings("unchecked")
  private void publishResponseEvent(WorkshopApprovalResponseEvent event) {
    domainEventPublisher.stream()
        .map(publisher -> (DomainEventPublisher<WorkshopApprovalResponseEvent>) publisher)
        .filter(publisher -> publisher.supports(event))
        .findFirst()
        .orElseThrow(() -> new WorkshopApplicationServiceException(
            "Workshop approval event publisher supporting %s event not found", event.getClass().getSimpleName()))
        .publish(event);
  }
}
