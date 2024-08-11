package com.eric218110.project.zeta.presentation.controllers.account_type;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eric218110.project.zeta.domain.http.account_type.ShowAccountTypeResponse;
import com.eric218110.project.zeta.domain.usecases.account_type.LoadAllAccountType;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("accountType")
public class ShowAccountTypeController {

  final LoadAllAccountType loadAllAccountType;

  @GetMapping("show")
  public Page<ShowAccountTypeResponse> show(JwtAuthenticationToken jwtAuthenticationToken,
      @PageableDefault(page = 0, size = 10) Pageable pageable) {

    return this.loadAllAccountType.listAllByUserUuid(pageable);
  }
}
