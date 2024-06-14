package com.eric218110.project.zeta.domain.usecases.card;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.eric218110.project.zeta.domain.http.card.ShowCardResponse;

public interface LoadAllCards {
  Page<ShowCardResponse> listAllByUserUuid(UUID userUuid, Pageable pageable);
}
