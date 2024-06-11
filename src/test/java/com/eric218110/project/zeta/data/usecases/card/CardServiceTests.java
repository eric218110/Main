package com.eric218110.project.zeta.data.usecases.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.eric218110.project.zeta.data.entities.card.CardEntity;
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
    List<CardEntity> cardEntities = CardServiceStub.loadFakeCardEntities();
    when(this.cardRepository.findByUser(any())).thenReturn(cardEntities);
    when(this.userRepository.findById(any())).thenReturn(CardServiceStub.loadUserEntity());

    List<ShowCardResponse> result = cardService.listAllByUserUuid(UUID.randomUUID());

    assertEquals(2, result.size());
  }

  @Test
  void listAllReturnsZeroWhenNotFindData() {
    when(this.cardRepository.findAll()).thenReturn(List.of());
    when(this.userRepository.findById(any())).thenReturn(CardServiceStub.loadUserEntity());

    List<ShowCardResponse> result = cardService.listAllByUserUuid(UUID.randomUUID());

    assertEquals(0, result.size());
  }

  @Test
  void shouldReturnValueCorrectOnSaveValue() {
    CardEntity cardEntityFaker = CardServiceStub.loadFakeCardEntity();
    when(this.cardRepository.save(any(CardEntity.class))).thenReturn(cardEntityFaker);
    when(this.userRepository.findById(any())).thenReturn(CardServiceStub.loadUserEntity());

    ShowCardResponse result = this.cardService.addCard(CardServiceStub.loadAddCardDto(),
        UUID.fromString(this.faker.internet().uuid()));

    assertEquals(cardEntityFaker.getName(), result.getName());
  }

}
