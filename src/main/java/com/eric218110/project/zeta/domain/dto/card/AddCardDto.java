package com.eric218110.project.zeta.domain.dto.card;

import com.eric218110.project.zeta.domain.enums.cardtype.CardTypesEnum;
import com.eric218110.project.zeta.validators.enums.CardTypeValidatorEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddCardDto {
  @NotBlank(message = "Field name is required")
  String name;

  @NotBlank(message = "Field color is required")
  String color;

  @Positive(message = "Field currentLimit is invalid")
  double currentLimit;

  @NotBlank(message = "Field flag is required")
  String flag;

  @NotBlank(message = "Field dueDate is required")
  String dueDate;

  @NotBlank(message = "Field closeDate is required")
  String closeDate;

  @CardTypeValidatorEnum(enumClass = CardTypesEnum.class, message = "Invalid card type")
  String type;
}
