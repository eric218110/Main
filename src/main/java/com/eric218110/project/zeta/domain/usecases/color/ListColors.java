package com.eric218110.project.zeta.domain.usecases.color;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.eric218110.project.zeta.domain.http.color.ShowColorsResponse;

public interface ListColors {
  Page<ShowColorsResponse> listColors(Pageable pageable);
}
