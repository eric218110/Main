package com.eric218110.project.zeta.domain.http.user;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddUserResponse {
  private UUID uuid;
  private String username;
  private List<String> roles;
}
