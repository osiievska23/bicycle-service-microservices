package org.vosiievska.bicycle.service.dataaccess.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Entity
@Table(name = "repair_service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "title")
public class RepairServiceEntity {

  @Id
  @Column(name = "title", length = 100, nullable = false)
  String title;

  @Column(name = "price", nullable = false)
  BigDecimal price;
}
