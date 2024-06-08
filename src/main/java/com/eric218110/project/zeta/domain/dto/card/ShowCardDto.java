package com.eric218110.project.zeta.domain.dto.card;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShowCardDto {
  private String uuid;
  private String name;
  private String color;
  private double currentLimit;
  private String flag;
  private String dueDate;
  private String closeDate;
  private String type;
}
