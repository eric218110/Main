package com.eric218110.project.zeta.presentation.controllers.cards;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import com.eric218110.project.zeta.domain.http.card.AddCardRequest;
import com.eric218110.project.zeta.domain.http.card.ShowCardResponse;
import com.eric218110.project.zeta.domain.usecases.card.AddOneCard;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("cards")
public class AddCardController {

  private final AddOneCard addOneCard;

  @PostMapping
  public ResponseEntity<ShowCardResponse> save(@Valid @RequestBody AddCardRequest addCardDto) {
    try {
      ShowCardResponse cardAdd = this.addOneCard.addCard(addCardDto);

      return ResponseEntity.status(HttpStatus.CREATED).body(cardAdd);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
          "Invalid request, fix values and try again");
    }
  }

}
