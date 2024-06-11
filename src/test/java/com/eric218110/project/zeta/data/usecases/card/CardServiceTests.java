package com.eric218110.project.zeta.data.usecases.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.eric218110.project.zeta.data.entities.card.CardEntity;
import com.eric218110.project.zeta.data.entities.user.UserEntity;
import com.eric218110.project.zeta.domain.enums.cardtype.CardTypesEnum;
import com.eric218110.project.zeta.domain.http.card.AddCardRequest;
import com.eric218110.project.zeta.domain.http.card.ShowCardResponse;
import com.eric218110.project.zeta.infra.repositories.database.card.CardRepository;
import com.eric218110.project.zeta.infra.repositories.database.user.UserRepository;
import com.github.javafaker.Faker;

class CardServiceTests {

  private Faker faker = new Faker();

  @Mock
  private CardRepository cardRepository;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  CardService cardService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void listAllReturnsValuesCorrect() {
    List<CardEntity> cardEntities = loadFakeCardEntities();
    when(this.cardRepository.findByUser(any())).thenReturn(cardEntities);
    when(this.userRepository.findById(any())).thenReturn(loadUserEntity());

    List<ShowCardResponse> result = cardService.listAllByUserUuid(UUID.randomUUID());

    assertEquals(2, result.size());
  }

  @Test
  void listAllReturnsZeroWhenNotFindData() {
    when(this.cardRepository.findAll()).thenReturn(List.of());
    when(this.userRepository.findById(any())).thenReturn(loadUserEntity());

    List<ShowCardResponse> result = cardService.listAllByUserUuid(UUID.randomUUID());

    assertEquals(0, result.size());
  }

  @Test
  void shouldReturnValueCorrectOnSaveValue() {
    CardEntity cardEntityFaker = this.loadFakeCardEntity();
    when(this.cardRepository.save(any(CardEntity.class))).thenReturn(cardEntityFaker);
    when(this.userRepository.findById(any())).thenReturn(loadUserEntity());

    ShowCardResponse result = this.cardService.addCard(this.loadAddCardDto(),
        UUID.fromString(this.faker.internet().uuid()));

    assertEquals(cardEntityFaker.getName(), result.getName());
  }

  private AddCardRequest loadAddCardDto() {
    return AddCardRequest.builder().name(faker.name().fullName()).color(faker.color().hex())
        .currentLimit(faker.number().randomDouble(10, 0, 100)).flag(faker.random().toString())
        .dueDate(faker.date().toString()).closeDate(faker.date().toString())
        .type(CardTypesEnum.CREDIT.toString()).build();
  }

  private List<CardEntity> loadFakeCardEntities() {

    CardEntity cardEntityOne = this.loadFakeCardEntity();
    CardEntity cardEntityTwo = this.loadFakeCardEntity();

    return List.of(cardEntityOne, cardEntityTwo);
  }

  private CardEntity loadFakeCardEntity() {

    return CardEntity.builder().uuid(faker.idNumber().toString()).name(faker.name().fullName())
        .color(faker.color().hex()).currentLimit(faker.number().randomDouble(10, 0, 100))
        .flag(faker.random().toString()).dueDate(faker.date().toString())
        .closeDate(faker.date().toString()).type(CardTypesEnum.CREDIT.toString()).build();
  }

  private Optional<UserEntity> loadUserEntity() {
    return Optional.of(UserEntity.builder().username(faker.internet().emailAddress())
        .password(faker.internet().password()).userId(UUID.randomUUID()).build());
  }
}
