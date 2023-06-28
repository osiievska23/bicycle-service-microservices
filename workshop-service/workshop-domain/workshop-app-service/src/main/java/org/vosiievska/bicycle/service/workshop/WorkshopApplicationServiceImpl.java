package org.vosiievska.bicycle.service.workshop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vosiievska.bicycle.service.workshop.dto.WorkshopApprovalRequest;
import org.vosiievska.bicycle.service.workshop.entity.WorkshopApprovalResponse;
import org.vosiievska.bicycle.service.workshop.event.BookingApprovedEvent;
import org.vosiievska.bicycle.service.workshop.event.BookingDeclinedEvent;
import org.vosiievska.bicycle.service.workshop.event.WorkshopApprovalResponseEvent;
import org.vosiievska.bicycle.service.workshop.mapper.WorkshopApprovalResponseMapper;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WorkshopApplicationServiceImpl implements WorkshopApplicationService {

  private final WorkshopApprovalResponseMapper mapper;
  private final WorkshopDomainService workshopDomainService;

  @Override
  public WorkshopApprovalResponseEvent createWorkshopApproval(WorkshopApprovalRequest request) {
    log.info("Received workshop approval event for booking id; {}", request.getBookingId());
    WorkshopApprovalResponse approvalResponse = mapper.workshopApprovalRequestToWorkshop(request);
    List<String> failureMessages = new ArrayList<>();
    workshopDomainService.validateWorkshopApproval(approvalResponse, failureMessages);
    return failureMessages.isEmpty() ? buildBooingApprovedEvent(approvalResponse)
        : buildBookingDeclinedEvent(approvalResponse, failureMessages);
  }

  private BookingApprovedEvent buildBooingApprovedEvent(WorkshopApprovalResponse approval) {
    log.info("Booking by id: {} approved by workshop with id: {}", approval.getBookingId().getValue(),
        approval.getWorkshop().getIdValue());
    return new BookingApprovedEvent(approval);
  }

  private BookingDeclinedEvent buildBookingDeclinedEvent(WorkshopApprovalResponse approval,
                                                         List<String> failureMessages) {
    log.info("Booking by id: {} declined by workshop with id: {}", approval.getBookingId().getValue(),
        approval.getWorkshop().getIdValue());
    return new BookingDeclinedEvent(approval, failureMessages);
  }
}
