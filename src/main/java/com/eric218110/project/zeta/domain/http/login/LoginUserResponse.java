package com.eric218110.project.zeta.domain.http.login;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class LoginUserResponse {
  private final String accessToken;
}
