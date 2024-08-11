package com.eric218110.project.zeta.domain.http.account_type;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShowAccountTypeResponse {
  private UUID uuid;
  private String name;
}

