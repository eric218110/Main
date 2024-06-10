package com.eric218110.project.zeta.data.usecases.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.eric218110.project.zeta.data.entities.role.RoleEntity;
import com.eric218110.project.zeta.data.entities.user.UserEntity;
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

  static public String makeHashPassword() {
    return UserServiceStub.faker.internet().uuid();
  }


  static public AddUserResponse makeAddUserResponseBody(String username) {
    return AddUserResponse.builder().uuid(UUID.fromString(UserServiceStub.faker.internet().uuid()))
        .username(username).roles(List.of(RoleEnum.USER.name())).build();
  }

  static public UserEntity makeUserEntity(String username, UUID uuid) {
    return UserEntity.builder().username(username)
        .password(UserServiceStub.faker.internet().password()).userId(uuid).build();
  }

  static public Optional<RoleEntity> makeRoleEntity() {
    return Optional.of(RoleEntity.builder().name(RoleEnum.USER.name()).build());
  }

}
