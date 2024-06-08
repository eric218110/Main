package com.eric218110.project.zeta.data.entities.card;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "cards")
public class CardEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String uuid;

  String name;
  String color;
  double currentLimit;
  String flag;
  String dueDate;
  String closeDate;
  String type;
}
