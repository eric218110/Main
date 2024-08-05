package com.eric218110.project.zeta.domain.http.account;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddAccountRequest {
  @NotBlank(message = "Field description is required")
  String description;

  @NotBlank(message = "Field institutionId is required")
  String institutionId;

  @Positive(message = "Field balance is invalid")
  BigDecimal balance;

  @NotBlank(message = "Field accountTypeId is invalid")
  String accountTypeId;

  @NotBlank(message = "Field colorId is required")
  String colorId;
}
