package org.vosiievska.bicycle.service.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vosiievska.bicycle.service.BookingDomainService;
import org.vosiievska.bicycle.service.domain.exception.EntityNotFoundException;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;
import org.vosiievska.bicycle.service.domain.valueobject.RepairServiceId;
import org.vosiievska.bicycle.service.dto.request.CreateBookingRequest;
import org.vosiievska.bicycle.service.dto.request.DeclineBookingRequest;
import org.vosiievska.bicycle.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.entity.Booking;
import org.vosiievska.bicycle.service.entity.RepairService;
import org.vosiievska.bicycle.service.entity.Workshop;
import org.vosiievska.bicycle.service.event.BookingCanceledEvent;
import org.vosiievska.bicycle.service.event.BookingCreatedEvent;
import org.vosiievska.bicycle.service.exception.BookingDomainException;
import org.vosiievska.bicycle.service.mapper.BookingMapper;
import org.vosiievska.bicycle.service.repository.BookingRepository;
import org.vosiievska.bicycle.service.repository.ClientRepository;
import org.vosiievska.bicycle.service.repository.RepairServiceRepository;
import org.vosiievska.bicycle.service.repository.WorkshopRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class BookingApplicationServiceImpl implements BookingApplicationService {

  private final BookingDomainService bookingDomainService;
  private final BookingRepository bookingRepository;
  private final ClientRepository clientRepository;
  private final WorkshopRepository workshopRepository;
  private final RepairServiceRepository repairServiceRepository;
  private final BookingMapper bookingMapper;

  @Override
  public BookingCreatedEvent createBooking(CreateBookingRequest request) {
    checkClientExistenceById(request.getCustomerId());
    Workshop workshop = getAvailableWorkshop();
    RepairService repairService = getRepairServiceById(request.getRepairServiceId());

    Booking booking = bookingMapper.createBookingRequestToEntity(request, workshop, repairService);
    Booking validatedBooking = bookingDomainService.validateAndInitiateBooking(booking, workshop);
    Booking savedBooking = bookingRepository.saveBooking(validatedBooking);
    workshopRepository.updateSpecialistStatusById(savedBooking.getSpecialistId(), true);

    log.info("New booking with id '{}' created by client id '{}'", savedBooking.getIdValue(), request.getCustomerId());
    return new BookingCreatedEvent(savedBooking);
  }

  @Override
  public BookingCanceledEvent cancelBooking(DeclineBookingRequest request) {
    checkClientExistenceById(request.getClientId());

    Booking booking = bookingRepository.findById(new BookingId(request.getBookingId()))
        .orElseThrow(() -> new EntityNotFoundException("Booking by id: %s not found", request.getBookingId()));

    booking.setCurrentStatus(BookingStatus.CANCELLED);
    booking.getFailureMessages().add(String.format("Cancelled by user with id: %s", request.getClientId()));
    bookingRepository.saveBooking(booking);

    workshopRepository.updateSpecialistStatusById(booking.getSpecialistId(), false);

    log.info("Cancelled booking with id '{}'", booking.getIdValue());
    return new BookingCanceledEvent(booking);
  }

  @Override
  public BookingStatusResponse getBookingStatus(BookingId bookingId) {
    log.info("Get booking status by id: {}", bookingId);
    return bookingRepository.findBookingStatusById(bookingId)
        .orElseThrow(() -> new EntityNotFoundException("Booking status by id: %s not found", bookingId));
  }

  private Workshop getAvailableWorkshop() {
    return workshopRepository.findAvailableWorkshopWithAvailableSpecialist()
        .orElseThrow(() -> new EntityNotFoundException("Available workshop not found"));
  }

  private void checkClientExistenceById(UUID customerId) {
    if (!clientRepository.existsById(new ClientId(customerId))) {
      throw new BookingDomainException("Client with id '%s' not found");
    }
  }

  private RepairService getRepairServiceById(String repairServiceId) {
    return repairServiceRepository.findById(new RepairServiceId(repairServiceId))
        .orElseThrow(() -> new EntityNotFoundException("Repair service by id '%s' not found", repairServiceId));
  }
}
