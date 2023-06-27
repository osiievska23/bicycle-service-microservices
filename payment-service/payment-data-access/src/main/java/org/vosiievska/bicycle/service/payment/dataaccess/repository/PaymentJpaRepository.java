package org.vosiievska.bicycle.service.payment.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vosiievska.bicycle.service.dataaccess.jpa.entity.PaymentEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID> {

  @Query("select p from PaymentEntity p where p.bookingId = ?1")
  Optional<PaymentEntity> findByBookingId(UUID bookingId);
}
