package com.eric218110.project.zeta.presentation.controllers.category;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eric218110.project.zeta.domain.http.category.AddCategoryRequest;
import com.eric218110.project.zeta.domain.http.category.ShowCategoryResponse;
import com.eric218110.project.zeta.domain.usecases.category.AddOneCategory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("categories")
public class AddCategoryController {

  private final AddOneCategory addOneCategory;

  @PostMapping
  public ResponseEntity<ShowCategoryResponse> save(
      @Valid @RequestBody AddCategoryRequest addCategoryRequest,
      JwtAuthenticationToken jwtAuthenticationToken) {

    var userUuid = UUID.fromString(jwtAuthenticationToken.getName());
    var category = this.addOneCategory.addCategory(addCategoryRequest, userUuid);

    return ResponseEntity.status(HttpStatus.CREATED).body(category);
  }

}
