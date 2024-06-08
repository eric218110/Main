package com.eric218110.project.zeta.domain.usecases.card;

import java.util.List;

import com.eric218110.project.zeta.domain.model.card.CardModel;

public interface LoadAllCards {
  List<CardModel> listAll();
}