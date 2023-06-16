package org.vosiievska.bicycle.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "specialist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpecialistEntity extends BaseUserEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workshop_id")
  WorkshopEntity workshop;

  @Column(name = "workshop_id", nullable = false)
  Integer workshopId;

  @Column(name = "busy", nullable = false)
  boolean busy;

}
