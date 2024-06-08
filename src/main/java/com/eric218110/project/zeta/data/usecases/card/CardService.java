package com.eric218110.project.zeta.data.usecases.card;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eric218110.project.zeta.data.entities.card.CardEntity;
import com.eric218110.project.zeta.domain.dto.card.AddCardDto;
import com.eric218110.project.zeta.domain.model.card.CardModel;
import com.eric218110.project.zeta.domain.usecases.card.AddOneCard;
import com.eric218110.project.zeta.domain.usecases.card.LoadAllCards;
import com.eric218110.project.zeta.infra.repositories.database.card.CardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CardService implements LoadAllCards, AddOneCard {
  private final CardRepository cardRepository;

  @Override
  public List<CardModel> listAll() {
    List<CardEntity> cardEntities = this.cardRepository.findAll();

    return cardEntities.stream().map(this::modelToEntity).collect(Collectors.toList());
  }

  @Override
  public CardModel addCard(AddCardDto addCardDto) {
    try {
      CardEntity cardEntity = this.addCardDtoToCardEntity(addCardDto);

      CardEntity cardSave = this.cardRepository.save(cardEntity);

      return this.modelToEntity(cardSave);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid request, fix values and try again");
    }
  }

  private CardEntity addCardDtoToCardEntity(AddCardDto addCardDto) {
    return CardEntity.builder()
        .name(addCardDto.getName())
        .color(addCardDto.getColor())
        .currentLimit(addCardDto.getCurrentLimit())
        .flag(addCardDto.getFlag())
        .dueDate(addCardDto.getDueDate())
        .closeDate(addCardDto.getCloseDate())
        .type(addCardDto.getType())
        .build();
  }

  private CardModel modelToEntity(CardEntity cardEntity) {
    return new CardModel(
        cardEntity.getUuid(),
        cardEntity.getColor(),
        cardEntity.getName(),
        cardEntity.getCurrentLimit(),
        cardEntity.getFlag(),
        cardEntity.getCloseDate(),
        cardEntity.getDueDate(),
        cardEntity.getType());
  }
}
