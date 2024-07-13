package com.eric218110.project.zeta.domain.http.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddUserRequestBody {
  @NotBlank(message = "Field username is required")
  private String username;

  @NotBlank(message = "Field password is required")
  private String password;

  @NotBlank(message = "Field name is required")
  private String name;
}
