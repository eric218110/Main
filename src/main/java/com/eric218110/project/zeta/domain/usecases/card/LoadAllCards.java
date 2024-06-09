package com.eric218110.project.zeta.domain.usecases.card;

import java.util.List;

import com.eric218110.project.zeta.domain.http.card.ShowCardResponse;

public interface LoadAllCards {
    List<ShowCardResponse> listAll();
}