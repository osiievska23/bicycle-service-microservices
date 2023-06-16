package org.vosiievska.bicycle.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class AddressEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  UUID id;

  @Column(name = "city", nullable = false, length = 45)
  String city;

  @Column(name = "street", nullable = false, length = 45)
  String street;

  @Column(name = "house_number", nullable = false, length = 20)
  String houseNumber;

  @Column(name = "district", nullable = false, length = 45)
  String district;

  @Column(name = "zip_code", nullable = false, length = 45)
  String zipCode;
}
