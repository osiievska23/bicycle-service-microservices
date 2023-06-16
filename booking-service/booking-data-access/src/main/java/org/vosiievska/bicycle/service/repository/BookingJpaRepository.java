package org.vosiievska.bicycle.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vosiievska.bicycle.service.dto.interfaces.BookingStatusInterface;
import org.vosiievska.bicycle.service.entity.BookingEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingJpaRepository extends JpaRepository<BookingEntity, UUID> {

  Optional<BookingStatusInterface> findBookingStatusById(UUID bookingId);

}
