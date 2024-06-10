package com.eric218110.project.zeta.domain.http.login;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class LoginUserBodyRequest {
  private final String username;
  private final String password;
}
