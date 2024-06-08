package com.eric218110.project.zeta.presentation.controllers.cards;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eric218110.project.zeta.domain.model.card.CardModel;
import com.eric218110.project.zeta.domain.usecases.card.LoadAllCards;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class ShowCardController {

  final LoadAllCards loadAllCards;

  @GetMapping("show")
  public ResponseEntity<List<CardModel>> show() {
    List<CardModel> listCards = this.loadAllCards.listAll();

    return ResponseEntity.ok(listCards);
  }

}
