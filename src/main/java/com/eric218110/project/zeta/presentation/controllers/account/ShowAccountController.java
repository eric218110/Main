package com.eric218110.project.zeta.presentation.controllers.account;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eric218110.project.zeta.domain.http.card.ShowCardResponse;
import com.eric218110.project.zeta.domain.usecases.card.LoadAllCards;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("account")
public class ShowAccountController {

  final LoadAllCards loadAllCards;

  @GetMapping("show")
  public Page<ShowCardResponse> show(JwtAuthenticationToken jwtAuthenticationToken,
      @PageableDefault(page = 0, size = 5) Pageable pageable) {

    var userUuid = UUID.fromString(jwtAuthenticationToken.getName());
    return this.loadAllCards.listAllByUserUuid(userUuid, pageable);

  }
}
