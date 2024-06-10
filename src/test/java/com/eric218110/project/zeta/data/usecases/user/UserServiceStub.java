package com.eric218110.project.zeta.data.usecases.user;

import java.util.List;
import com.eric218110.project.zeta.domain.enums.role.RoleEnum;
import com.eric218110.project.zeta.domain.http.user.AddUserRequestBody;
import com.eric218110.project.zeta.domain.http.user.AddUserResponse;
import com.github.javafaker.Faker;

public class UserServiceStub {

  private static final Faker faker = new Faker();

  static public AddUserRequestBody makeAddUserRequestBody() {
    return AddUserRequestBody.builder().username(UserServiceStub.faker.internet().emailAddress())
        .password(UserServiceStub.faker.internet().password()).build();
  }

  static public AddUserResponse makeAddUserResponseBody() {
    return AddUserResponse.builder().username(UserServiceStub.faker.internet().emailAddress())
        .password(UserServiceStub.faker.internet().password()).roles(List.of(RoleEnum.USER.name()))
        .build();
  }

}
