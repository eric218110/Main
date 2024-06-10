package com.eric218110.project.zeta.domain.http.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddUserRequestBody {
  private String username;
  private String password;
}
