package com.eric218110.project.zeta.domain.usecases.card;

import com.eric218110.project.zeta.domain.dto.card.AddCardDto;
import com.eric218110.project.zeta.domain.dto.card.ShowCardDto;

public interface AddOneCard {
    ShowCardDto addCard(AddCardDto addCardDto);
}