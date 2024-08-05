package com.eric218110.project.zeta.domain.http.color;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShowColorsByAccountRequest {
  private String accountUuid;
}

