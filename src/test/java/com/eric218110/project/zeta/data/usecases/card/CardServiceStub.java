package com.eric218110.project.zeta.data.usecases.card;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.eric218110.project.zeta.domain.entities.card.CardEntity;
import com.eric218110.project.zeta.domain.entities.user.UserEntity;
import com.eric218110.project.zeta.domain.enums.cardtype.CardTypesEnum;
import com.eric218110.project.zeta.domain.http.card.AddCardRequest;
import com.github.javafaker.Faker;

public class CardServiceStub {
  private final static Faker faker = new Faker();

  static AddCardRequest loadAddCardDto() {
    return AddCardRequest.builder().name(CardServiceStub.faker.name().fullName())
        .color(CardServiceStub.faker.color().hex())
        .currentLimit(CardServiceStub.faker.number().randomDouble(10, 0, 100))
        .flag(CardServiceStub.faker.random().toString())
        .dueDate(CardServiceStub.faker.date().toString())
        .closeDate(CardServiceStub.faker.date().toString()).type(CardTypesEnum.CREDIT.toString())
        .build();
  }

  static List<CardEntity> loadFakeCardEntities() {

    CardEntity cardEntityOne = CardServiceStub.loadFakeCardEntity();
    CardEntity cardEntityTwo = CardServiceStub.loadFakeCardEntity();

    return List.of(cardEntityOne, cardEntityTwo);
  }

  static CardEntity loadFakeCardEntity() {

    return CardEntity.builder().uuid(CardServiceStub.faker.idNumber().toString())
        .name(CardServiceStub.faker.name().fullName()).color(CardServiceStub.faker.color().hex())
        .currentLimit(CardServiceStub.faker.number().randomDouble(10, 0, 100))
        .flag(CardServiceStub.faker.random().toString())
        .dueDate(CardServiceStub.faker.date().toString())
        .closeDate(CardServiceStub.faker.date().toString()).type(CardTypesEnum.CREDIT.toString())
        .build();
  }

  static Optional<UserEntity> loadUserEntity() {
    return Optional.of(UserEntity.builder()
        .username(CardServiceStub.faker.internet().emailAddress())
        .password(CardServiceStub.faker.internet().password()).userId(UUID.randomUUID()).build());
  }
}
