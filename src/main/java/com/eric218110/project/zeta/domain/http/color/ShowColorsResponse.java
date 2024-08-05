package com.eric218110.project.zeta.domain.http.color;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShowColorsResponse {
  private UUID uuid;
  private String name;
  private String description;
  private String hex;
  private String argb;
}

