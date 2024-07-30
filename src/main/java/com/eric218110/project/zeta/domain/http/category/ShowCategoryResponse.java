package com.eric218110.project.zeta.domain.http.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShowCategoryResponse {
  private String uuid;
  private String name;
}
