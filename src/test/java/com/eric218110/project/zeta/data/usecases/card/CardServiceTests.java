package com.eric218110.project.zeta.data.usecases.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.eric218110.project.zeta.domain.entities.card.CardEntity;
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
    Page<CardEntity> cardEntities = CardServiceStub.loadFakeCardEntities();
    when(this.cardRepository.findByUser(any(), any())).thenReturn(cardEntities);
    when(this.userRepository.findById(any())).thenReturn(CardServiceStub.loadUserEntity());
    Pageable pageable = Pageable.unpaged();

    var result = cardService.listAllByUserUuid(UUID.randomUUID(), pageable);

    assertEquals(2, result.getSize());
  }

  @Test
  void listAllReturnsZeroWhenNotFindData() {
    Page<CardEntity> cardEntities = CardServiceStub.loadFakeCardEntitiesEmpty();
    when(this.cardRepository.findByUser(any(), any())).thenReturn(cardEntities);
    when(this.userRepository.findById(any())).thenReturn(CardServiceStub.loadUserEntity());
    Pageable pageable = Pageable.unpaged();

    var result = cardService.listAllByUserUuid(UUID.randomUUID(), pageable);

    assertEquals(0, result.getSize());
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
