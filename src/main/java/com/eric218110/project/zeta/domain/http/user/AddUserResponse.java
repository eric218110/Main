package com.eric218110.project.zeta.domain.http.user;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddUserResponse {
  private String username;
  private String password;
  private List<String> roles;
}
