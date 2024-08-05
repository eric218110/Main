package com.eric218110.project.zeta.presentation.controllers.colors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eric218110.project.zeta.domain.http.color.ShowColorsResponse;
import com.eric218110.project.zeta.domain.usecases.color.ListColors;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("colors")
public class ShowColorsController {

  final ListColors listColors;

  @GetMapping("show")
  public Page<ShowColorsResponse> show(Pageable pageable) {

    return this.listColors.listColors(pageable);
  }
}
