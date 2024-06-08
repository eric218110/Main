package com.eric218110.project.zeta.domain.usecases.card;

import com.eric218110.project.zeta.domain.dto.card.AddCardDto;
import com.eric218110.project.zeta.domain.model.card.CardModel;

public interface AddOneCard {
  CardModel addCard(AddCardDto addCardDto);
}