package com.eric218110.project.zeta.presentation.controllers.cards;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eric218110.project.zeta.domain.http.card.AddCardRequest;
import com.eric218110.project.zeta.domain.http.card.ShowCardResponse;
import com.eric218110.project.zeta.domain.usecases.card.AddOneCard;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("cards")
public class AddCardController {

  private final AddOneCard addOneCard;

  @PostMapping
  public ResponseEntity<ShowCardResponse> save(@Valid @RequestBody AddCardRequest addCardDto,
      JwtAuthenticationToken jwtAuthenticationToken) {

    UUID userUuid = UUID.fromString(jwtAuthenticationToken.getName());
    ShowCardResponse cardAdd = this.addOneCard.addCard(addCardDto, userUuid);

    return ResponseEntity.status(HttpStatus.CREATED).body(cardAdd);
  }

}
