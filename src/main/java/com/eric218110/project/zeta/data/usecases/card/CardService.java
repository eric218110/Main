package com.eric218110.project.zeta.data.usecases.card;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.eric218110.project.zeta.domain.entities.card.CardEntity;
import com.eric218110.project.zeta.domain.entities.user.UserEntity;
import com.eric218110.project.zeta.domain.http.card.AddCardRequest;
import com.eric218110.project.zeta.domain.http.card.ShowCardResponse;
import com.eric218110.project.zeta.domain.usecases.card.AddOneCard;
import com.eric218110.project.zeta.domain.usecases.card.LoadAllCards;
import com.eric218110.project.zeta.infra.repositories.database.card.CardRepository;
import com.eric218110.project.zeta.infra.repositories.database.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CardService implements LoadAllCards, AddOneCard {
  private final CardRepository cardRepository;
  private final UserRepository userRepository;

  @Override
  public List<ShowCardResponse> listAllByUserUuid(UUID userIUuid) {
    UserEntity userEntity = this.loadUserByUuid(userIUuid);
    List<CardEntity> cardEntities = this.cardRepository.findByUser(userEntity);

    return cardEntities.stream().map(this::entityToDto).collect(Collectors.toList());
  }

  @Override
  public ShowCardResponse addCard(AddCardRequest addCardDto, UUID userIUuid) {
    UserEntity userEntity = this.loadUserByUuid(userIUuid);
    CardEntity cardEntity = this.addCardDtoToCardEntity(addCardDto, userEntity);
    CardEntity cardSave = this.cardRepository.save(cardEntity);

    return this.entityToDto(cardSave);

  }

  private UserEntity loadUserByUuid(UUID userUuid) {
    Optional<UserEntity> userEntity = this.userRepository.findById(userUuid);

    if (userEntity.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found user");
    }

    return userEntity.get();
  }

  private CardEntity addCardDtoToCardEntity(AddCardRequest addCardDto, UserEntity userEntity) {
    return CardEntity.builder().name(addCardDto.getName()).color(addCardDto.getColor())
        .currentLimit(addCardDto.getCurrentLimit()).flag(addCardDto.getFlag())
        .dueDate(addCardDto.getDueDate()).closeDate(addCardDto.getCloseDate()).user(userEntity)
        .type(addCardDto.getType()).build();
  }

  private ShowCardResponse entityToDto(CardEntity cardEntity) {
    return ShowCardResponse.builder().uuid(cardEntity.getUuid()).name(cardEntity.getName())
        .color(cardEntity.getColor()).currentLimit(cardEntity.getCurrentLimit())
        .flag(cardEntity.getFlag()).dueDate(cardEntity.getDueDate())
        .closeDate(cardEntity.getCloseDate()).type(cardEntity.getType()).build();
  }
}
