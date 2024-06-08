package com.eric218110.project.zeta.domain.model.card;

import lombok.Data;

@Data
public class CardModel {
  private final String uuid;
  private final String color;
  private final String name;
  private final double currentLimit;
  private final String flag;
  private final String dueDate;
  private final String closeDate;
  private final String type;

}
