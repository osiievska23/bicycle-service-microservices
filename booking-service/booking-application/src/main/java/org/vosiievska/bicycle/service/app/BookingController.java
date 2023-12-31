package org.vosiievska.bicycle.service.app;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vosiievska.bicycle.service.domain.service.dto.request.CancelBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.request.CreateBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.domain.service.facade.BookingApplicationFacade;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/booking")
public class BookingController {

  private final BookingApplicationFacade applicationFacade;

  @PostMapping
  public ResponseEntity<BookingStatusResponse> createBooking(@Valid @RequestBody CreateBookingRequest request) {
    log.info("Rest request to create new booking for client with id: {} and repair service id: {}",
        request.getClientId(), request.getRepairServiceId());
    return ResponseEntity.ok(applicationFacade.createBooking(request));
  }

  @PutMapping("/{bookingId}")
  public ResponseEntity<BookingStatusResponse> cancelBooking(@NotNull @PathVariable UUID bookingId,
                                                             @RequestParam UUID clientId) {// todo: get client ID from session
    log.info("Rest request to cancel booking with id: {} by client id: {}", bookingId, clientId);
    return ResponseEntity.ok(applicationFacade.cancelBooking(new CancelBookingRequest(bookingId, clientId)));
  }

  @GetMapping("/{bookingId}")
  public ResponseEntity<BookingStatusResponse> getBookingStatusById(@NotNull @PathVariable UUID bookingId) {
    log.info("Rest request to get current status of booking with id: {}", bookingId);
    return ResponseEntity.ok(applicationFacade.getBookingStatusById(new BookingId(bookingId)));
  }
}
