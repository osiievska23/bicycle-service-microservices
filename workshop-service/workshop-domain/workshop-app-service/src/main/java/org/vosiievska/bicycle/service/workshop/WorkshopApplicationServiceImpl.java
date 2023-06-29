package org.vosiievska.bicycle.service.workshop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.workshop.dto.WorkshopApprovalRequest;
import org.vosiievska.bicycle.service.workshop.entity.Workshop;
import org.vosiievska.bicycle.service.workshop.entity.WorkshopApprovalResponse;
import org.vosiievska.bicycle.service.workshop.event.BookingApprovedEvent;
import org.vosiievska.bicycle.service.workshop.event.BookingDeclinedEvent;
import org.vosiievska.bicycle.service.workshop.event.WorkshopApprovalResponseEvent;
import org.vosiievska.bicycle.service.workshop.exception.WorkshopDomainException;
import org.vosiievska.bicycle.service.workshop.mapper.WorkshopApprovalResponseMapper;
import org.vosiievska.bicycle.service.workshop.repository.WorkshopRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.vosiievska.bicycle.service.domain.valueobject.WorkshopResponseStatus.APPROVED;
import static org.vosiievska.bicycle.service.domain.valueobject.WorkshopResponseStatus.DECLINED;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WorkshopApplicationServiceImpl implements WorkshopApplicationService {

  private final WorkshopApprovalResponseMapper mapper;
  private final WorkshopDomainService workshopDomainService;
  private final WorkshopRepository workshopRepository;

  @Override
  public WorkshopApprovalResponseEvent createBookingApprovedEvent(WorkshopApprovalRequest request) {
    log.info("Received workshop approval event for booking id; {}", request.getBookingId());
    List<String> failureMessages = new ArrayList<>();

    WorkshopApprovalResponse approvalResponse = mapper.workshopApprovalRequestToWorkshop(request);

    Optional<Workshop> workshopOptional = workshopRepository.findAvailableWorkshopWithAvailableSpecialist();

    if (workshopOptional.isEmpty()) {
      failureMessages.add("Available workshop not found");
      return buildBookingDeclinedEvent(approvalResponse, failureMessages);
    }
    approvalResponse.setWorkshop(workshopOptional.get());
    workshopDomainService.validateWorkshopApproval(approvalResponse, failureMessages);

    if (!failureMessages.isEmpty()) {
      return buildBookingDeclinedEvent(approvalResponse, failureMessages);
    }
    SpecialistId specialistId = getAvailableSpecialistId(approvalResponse);
    approvalResponse.setSpecialistId(specialistId);
    workshopRepository.updateSpecialistStatusById(specialistId, true);
    return buildBooingApprovedEvent(approvalResponse);
  }

  @Override
  public void updateWorkshopSpecialistStatus(BookingId bookingId) {
    log.info("Update specialist busy status to 'false' by booking id: {}", bookingId.getValue());
    workshopRepository.updateSpecialistStatusByBookingId(bookingId, false);
  }

  private BookingApprovedEvent buildBooingApprovedEvent(WorkshopApprovalResponse approval) {
    log.info("Booking by id: {} approved by workshop with id: {}", approval.getBookingId().getValue(),
        approval.getWorkshop().getIdValue());
    approval.setApprovalStatus(APPROVED);
    return new BookingApprovedEvent(approval);
  }

  private BookingDeclinedEvent buildBookingDeclinedEvent(WorkshopApprovalResponse approval,
                                                         List<String> failureMessages) {
    log.info("Booking by id: {} declined, with failure messages: {}", approval.getBookingId().getValue(),
        String.join(",", failureMessages));
    approval.setApprovalStatus(DECLINED);
    return new BookingDeclinedEvent(approval, failureMessages);
  }

  private SpecialistId getAvailableSpecialistId(WorkshopApprovalResponse approvalResponse) {
    return approvalResponse.getWorkshop().getSpecialists()
        .stream()
        .filter(s -> !s.isBusy())
        .findFirst()
        .orElseThrow(() -> new WorkshopDomainException(
            "Available specialist not found for booking by id: %s!", approvalResponse.getBookingId().getValue()))
        .getId();
  }
}
