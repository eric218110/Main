package com.eric218110.project.zeta.domain.http.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class LoginUserBodyRequest {
  @NotBlank(message = "Field username is required")
  private final String username;

  @NotBlank(message = "Field password is required")
  private final String password;
}
