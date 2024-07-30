package com.eric218110.project.zeta.domain.http.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCategoryRequest {
  @NotBlank(message = "Field name is required")
  String name;
}
