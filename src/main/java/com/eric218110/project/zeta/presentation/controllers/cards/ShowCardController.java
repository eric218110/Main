package com.eric218110.project.zeta.presentation.controllers.cards;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eric218110.project.zeta.domain.http.card.ShowCardResponse;
import com.eric218110.project.zeta.domain.usecases.card.LoadAllCards;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("cards")
public class ShowCardController {

  final LoadAllCards loadAllCards;

  @GetMapping("show")
  public ResponseEntity<List<ShowCardResponse>> show(
      JwtAuthenticationToken jwtAuthenticationToken) {

    UUID userUuid = UUID.fromString(jwtAuthenticationToken.getName());
    List<ShowCardResponse> listCards = this.loadAllCards.listAllByUserUuid(userUuid);

    return ResponseEntity.ok(listCards);
  }

}
