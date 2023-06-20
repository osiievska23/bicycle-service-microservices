package org.vosiievska.bicycle.service.dataaccess.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "booking", schema = "service")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class BookingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", referencedColumnName = "id", insertable = false,
      updatable = false, foreignKey = @ForeignKey(name = "fk_booking_client_id"))
  ClientEntity client;

  @Column(name = "client_id", insertable = false, updatable = false)
  UUID clientId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workshop_id", referencedColumnName = "id", insertable = false,
      updatable = false, foreignKey = @ForeignKey(name = "fk_booking_workshop_id"))
  WorkshopEntity workshop;

  @Column(name = "workshop_id", insertable = false, updatable = false)
  Integer workshopId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "specialist_id", referencedColumnName = "id", insertable = false,
      updatable = false, foreignKey = @ForeignKey(name = "fk_booking_specialist_id"))
  SpecialistEntity specialist;

  @Column(name = "specialist_id", insertable = false, updatable = false)
  UUID specialistId;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  AddressEntity address;

  @Column(name = "address_id", insertable = false, updatable = false)
  UUID addressId;

  @ManyToOne
  @JoinColumn(name = "repair_service_title", referencedColumnName = "title", insertable = false,
      updatable = false, foreignKey = @ForeignKey(name = "fk_booking_repair_service_title"))
  RepairServiceEntity repairService;

  @Column(name = "current_status", length = 45, nullable = false)
  String currentStatus;

  //  @ElementCollection
  //  @CollectionTable(name = "booking_failure_messages", joinColumns = @JoinColumn(name = "booking_id"))
  //  @Column(name = "failure_messages")
  //  Set<String> failureMessages;

  @Column(name = "failure_messages")
  String failureMessages;

  @Column(name = "updated_at", nullable = false, updatable = false)
  @CreationTimestamp
  Instant updatedAt;

}
