package org.vosiievska.bicycle.service.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vosiievska.bicycle.service.BookingDomainService;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;
import org.vosiievska.bicycle.service.domain.valueobject.RepairServiceId;
import org.vosiievska.bicycle.service.dto.request.CreateBookingRequest;
import org.vosiievska.bicycle.service.dto.request.DeclineBookingRequest;
import org.vosiievska.bicycle.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.entity.Booking;
import org.vosiievska.bicycle.service.entity.RepairService;
import org.vosiievska.bicycle.service.entity.Workshop;
import org.vosiievska.bicycle.service.event.BookingCreatedEvent;
import org.vosiievska.bicycle.service.exception.BookingDomainException;
import org.vosiievska.bicycle.service.mapper.BookingMapper;
import org.vosiievska.bicycle.service.repository.BookingRepository;
import org.vosiievska.bicycle.service.repository.ClientRepository;
import org.vosiievska.bicycle.service.repository.RepairServiceRepository;
import org.vosiievska.bicycle.service.repository.WorkshopRepository;

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
    checkClientExistenceById(request.getClientId());
    Workshop workshop = getAvailableWorkshop();
    RepairService repairService = getRepairServiceById(request.getRepairServiceId());

    Booking booking = bookingMapper.createBookingRequestToEntity(request, workshop, repairService);
    Booking validatedBooking = bookingDomainService.validateAndInitiateBooking(booking, workshop);
    Booking savedBooking = saveBooking(validatedBooking);
    workshopRepository.makeSpecialistBusyById(savedBooking.getSpecialistId());

    log.info("New Booking with id '{}' created by client id '{}'", savedBooking.getIdValue(), request.getClientId());
    return new BookingCreatedEvent(savedBooking);
  }

  @Override
  public BookingStatusResponse cancelBooking(DeclineBookingRequest request) {
    return null;
  }

  @Override
  public BookingStatusResponse getBookingStatus(BookingId bookingId) {
    log.info("Get booking status by id: {}", bookingId);
    return bookingRepository.findBookingStatusById(bookingId)
        .orElseThrow(() -> new BookingDomainException("Could not get booking status"));
  }

  private Workshop getAvailableWorkshop() {
    return workshopRepository.findAvailableWorkshopWithAvailableSpecialist()
        .orElseThrow(() -> new BookingDomainException("Available workshop not found"));
  }

  private void checkClientExistenceById(UUID clientId) {
    if (!clientRepository.existsById(new ClientId(clientId))) {
      throw new BookingDomainException("Client with id '%s' not found");
    }
  }

  private RepairService getRepairServiceById(String repairServiceId) {
    return repairServiceRepository.findById(new RepairServiceId(repairServiceId))
        .orElseThrow(() -> new BookingDomainException("Repair service by id '%s' not found", repairServiceId));
  }

  private Booking saveBooking(Booking newBooking) {
    return bookingRepository.saveBooking(newBooking)
        .orElseThrow(() -> new BookingDomainException("Could not save the booking"));
  }
}
