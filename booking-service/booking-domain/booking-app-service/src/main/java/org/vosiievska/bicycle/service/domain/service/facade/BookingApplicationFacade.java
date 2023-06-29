package org.vosiievska.bicycle.service.domain.service.facade;

import org.vosiievska.bicycle.service.domain.service.dto.request.CreateBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.request.CancelBookingRequest;
import org.vosiievska.bicycle.service.domain.service.dto.response.BookingStatusResponse;
import org.vosiievska.bicycle.service.domain.service.dto.response.WorkshopResponse;
import org.vosiievska.bicycle.service.domain.valueobject.BookingId;
import org.vosiievska.bicycle.service.domain.valueobject.BookingStatus;

import java.util.List;

public interface BookingApplicationFacade {

  BookingStatusResponse createBooking(CreateBookingRequest request);

  BookingStatusResponse cancelBooking(CancelBookingRequest bookingId);

  BookingStatusResponse getBookingStatusById(BookingId bookingId);

  void payBooking(WorkshopResponse workshopResponse);

  void updateBookingStatus(BookingId bookingId, BookingStatus bookingStatus);

  void updateBookingStatus(BookingId bookingId, BookingStatus bookingStatus, List<String> failureMessages);
}
