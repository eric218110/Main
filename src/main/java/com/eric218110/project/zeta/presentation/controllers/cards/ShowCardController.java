package com.eric218110.project.zeta.presentation.controllers.cards;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eric218110.project.zeta.domain.dto.card.ShowCardDto;
import com.eric218110.project.zeta.domain.usecases.card.LoadAllCards;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class ShowCardController {

  final LoadAllCards loadAllCards;

  @GetMapping("show")
  public ResponseEntity<List<ShowCardDto>> show() {
    List<ShowCardDto> listCards = this.loadAllCards.listAll();

    return ResponseEntity.ok(listCards);
  }

}
