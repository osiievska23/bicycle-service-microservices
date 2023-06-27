package org.vosiievska.bicycle.service.dataaccess.jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment", schema = "service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class PaymentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "client_id", insertable=false, updatable=false,
      foreignKey = @ForeignKey(name = "fk_payment_client_id"))
  ClientEntity clientEntity;

  @Column(name = "client_id", nullable = false)
  UUID clientId;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "booking_id", insertable=false, updatable=false,
      foreignKey = @ForeignKey(name = "fk_payment_booking_id"))
  BookingEntity bookingEntity;

  @Column(name = "booking_id", nullable = false)
  UUID bookingId;

  @Column(name = "price", nullable = false)
  BigDecimal price;

  @Column(name = "current_status", length = 45, nullable = false)
  String currentStatus;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreationTimestamp
  LocalDateTime createdAt;
}
