package com.eric218110.project.zeta.domain.usecases.category;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.eric218110.project.zeta.domain.http.category.ShowCategoryResponse;

public interface LoadAllCategory {
  Page<ShowCategoryResponse> listAllByUserUuid(UUID userUuid, Pageable pageable);
}
