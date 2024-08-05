package com.eric218110.project.zeta.domain.usecases.color;

import com.eric218110.project.zeta.domain.entities.colors.ColorsEntity;

public interface LoadColorByUuid {
  ColorsEntity loadColorByUuid(String uuid);
}
