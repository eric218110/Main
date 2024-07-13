package com.eric218110.project.zeta.domain.http.login;

import java.util.List;
import lombok.Data;

@Data
public class LoginUserResponse {
  private String accessToken;
  private String userName;
  private String name;
  private String uuid;
  private List<String> roles;
}
