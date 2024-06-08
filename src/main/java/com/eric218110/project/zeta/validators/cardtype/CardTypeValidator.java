package com.eric218110.project.zeta.validators.cardtype;

import java.util.Arrays;

import com.eric218110.project.zeta.validators.enums.CardTypeValidatorEnum;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CardTypeValidator implements ConstraintValidator<CardTypeValidatorEnum, String> {
  private Class<? extends Enum<?>> enumClass;

  @Override
  public void initialize(CardTypeValidatorEnum constraintAnnotation) {
    this.enumClass = constraintAnnotation.enumClass();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true; // Considera null como válido, use @NotNull para validação de null separadamente
    }

    return Arrays.stream(enumClass.getEnumConstants())
        .anyMatch(enumConstant -> enumConstant.name().equals(value));
  }

}
