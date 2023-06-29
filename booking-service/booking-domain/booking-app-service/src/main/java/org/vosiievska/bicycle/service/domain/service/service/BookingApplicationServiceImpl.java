package org.vosiievska.bicycle.service.domain.service.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vosiievska.bicycle.service.domain.core.BookingDomainService;
import org.vosiievska.bicycle.service.domain.core.entity.Booking;
import org.vosiievska.bicycle.service.domain.core.entity.Client;
import org.vosiievska.bicycle.service.domain.core.entity.RepairService;
import org.vosiievska.bicycle.service.domain.core.event.BookingCanceledEvent;
import org.vosiievska.bicycle.service.domain.core.event.BookingCreatedEvent;
import org.vosiievska.bicycle.service.domain.core.exception.BookingDomainException;
import org.vosiievska.bicycle.service.domain.exception.EntityNotFoundException;
import org.vosiievska.bicycle.service.domain.service.dto.request.CancelBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.request.CreateBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.domain.service.dto.response.WorkshopResponse;
import org.vosiievska.bicycle.service.domain.service.mapper.BookingMapper;
import org.vosiievska.bicycle.service.domain.service.repository.BookingRepository;
import org.vosiievska.bicycle.service.domain.service.repository.ClientRepository;
import org.vosiievska.bicycle.service.domain.service.repository.RepairServiceRepository;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;
import org.vosiievska.bicycle.service.domain.valueobject.ClientId;
import org.vosiievska.bicycle.service.domain.valueobject.RepairServiceId;
import org.vosiievska.bicycle.service.domain.valueobject.SpecialistId;
import org.vosiievska.bicycle.service.domain.valueobject.WorkshopId;

import java.util.List;
import java.util.UUID;

import static org.vosiievska.bicycle.service.domain.valueobject.BookingStatus.CANCELLING;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class BookingApplicationServiceImpl implements BookingApplicationService {

  private final BookingDomainService bookingDomainService;
  private final BookingRepository bookingRepository;
  private final ClientRepository clientRepository;
  private final RepairServiceRepository repairServiceRepository;
  private final BookingMapper bookingMapper;

  @Override
  public BookingCreatedEvent createBooking(CreateBookingRequest request) {
    Client client = findClientWithAddressById(request.getClientId());
    RepairService repairService = getRepairServiceById(request.getRepairServiceId());

    Booking booking = bookingMapper.createBookingRequestToEntity(client, repairService);
    Booking validatedBooking = bookingDomainService.validateAndInitiateBooking(booking);
    Booking savedBooking = bookingRepository.saveBooking(validatedBooking);

    log.info("New booking with id '{}' created by client id '{}'", savedBooking.getIdValue(), request.getClientId());
    return new BookingCreatedEvent(savedBooking);
  }

  @Override
  public BookingCanceledEvent cancelBooking(CancelBookingRequest request) {
    checkClientExistenceById(request.getClientId());

    Booking booking = bookingRepository.findById(new BookingId(request.getBookingId()))
        .orElseThrow(() -> new EntityNotFoundException("Booking by id: %s not found", request.getBookingId()));

    List<String> failureMessages = List.of(String.format("Cancelled by user with id: %s", request.getClientId()));
    booking.updateCurrentStatusWithFailure(CANCELLING, failureMessages);
    bookingRepository.saveBooking(booking);

    log.info("Cancelled booking with id '{}'", booking.getIdValue());
    return new BookingCanceledEvent(booking);
  }

  @Override
  public BookingStatusResponse getBookingStatus(BookingId bookingId) {
    log.info("Get booking status by id: {}", bookingId.getValue());
    return bookingRepository.findBookingStatusById(bookingId)
        .orElseThrow(() -> new EntityNotFoundException("Booking status by id: %s not found", bookingId));
  }

  @Override
  public Booking getBookingById(BookingId bookingId) {
    log.info("Get booking by id: {}", bookingId.getValue());
    return bookingRepository.findById(bookingId)
        .orElseThrow(() -> new EntityNotFoundException("Booking by id: %s not found", bookingId.getValue()));

  }

  @Override
  public void updateBookingStatus(BookingId bookingId, BookingStatus bookingStatus) {
    Booking booking = getBookingById(bookingId);
    booking.updateCurrentStatus(bookingStatus);
    bookingRepository.saveBooking(booking);
  }

  @Override
  public void updateBookingStatus(BookingId bookingId, BookingStatus bookingStatus, List<String> failureMessages) {
    Booking booking = getBookingById(bookingId);
    booking.updateCurrentStatusWithFailure(bookingStatus, failureMessages);
    bookingRepository.saveBooking(booking);
  }

  @Override
  public Booking updateBooking(WorkshopResponse workshopResponse) {
    BookingId bookingId = new BookingId(workshopResponse.getBookingId());
    Booking booking = getBookingById(bookingId);
    booking.updateCurrentStatus(BookingStatus.APPROVED);
    booking.setWorkshopId(new WorkshopId(workshopResponse.getWorkshopId()));
    booking.setSpecialistId(new SpecialistId(workshopResponse.getSpecialistId()));
    return bookingRepository.saveBooking(booking);
  }

  private Client findClientWithAddressById(UUID clientId) {
    return clientRepository.findClientWithAddressById(clientId)
        .orElseThrow(() -> new EntityNotFoundException("Client with id '%s' not found", clientId));
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
