package com.eric218110.project.zeta.data.usecases.card;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

  static Page<CardEntity> loadFakeCardEntities() {

    CardEntity cardEntityOne = CardServiceStub.loadFakeCardEntity();

    Pageable pageable = PageRequest.of(0, 2);
    Page<CardEntity> page = new PageImpl<>(Collections.singletonList(cardEntityOne), pageable, 1);

    return page;
  }

  static Page<CardEntity> loadFakeCardEntitiesEmpty() {

    Pageable pageable = Pageable.unpaged();
    Page<CardEntity> page = new PageImpl<>(Collections.emptyList(), pageable, 1);

    return page;
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
