package com.eric218110.project.zeta.domain.usecases.category;

import java.util.UUID;
import com.eric218110.project.zeta.domain.http.category.AddCategoryRequest;
import com.eric218110.project.zeta.domain.http.category.ShowCategoryResponse;

public interface AddOneCategory {
  ShowCategoryResponse addCategory(AddCategoryRequest addCategoryDto, UUID userUuid);
}
