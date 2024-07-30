package com.eric218110.project.zeta.presentation.controllers.category;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eric218110.project.zeta.domain.http.category.ShowCategoryResponse;
import com.eric218110.project.zeta.domain.usecases.category.LoadAllCategory;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
public class ShowCategoryController {

  final LoadAllCategory loadAllCategory;

  @GetMapping("show")
  public Page<ShowCategoryResponse> show(JwtAuthenticationToken jwtAuthenticationToken,
      @PageableDefault(page = 0, size = 5) Pageable pageable) {

    var userUuid = UUID.fromString(jwtAuthenticationToken.getName());
    return this.loadAllCategory.listAllByUserUuid(userUuid, pageable);

  }

}
