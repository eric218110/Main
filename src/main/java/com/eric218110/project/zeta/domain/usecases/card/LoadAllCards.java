package com.eric218110.project.zeta.domain.usecases.card;

import java.util.List;

import com.eric218110.project.zeta.domain.dto.card.ShowCardDto;

public interface LoadAllCards {
    List<ShowCardDto> listAll();
}