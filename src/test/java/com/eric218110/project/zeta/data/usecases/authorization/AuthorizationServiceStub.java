package com.eric218110.project.zeta.data.usecases.authorization;

import java.time.Instant;
import java.util.Optional;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import com.eric218110.project.zeta.domain.entities.user.UserEntity;
import com.eric218110.project.zeta.domain.http.login.LoginUserBodyRequest;
import com.eric218110.project.zeta.domain.http.login.LoginUserResponse;
import com.github.javafaker.Faker;

public class AuthorizationServiceStub {
  static Faker faker = new Faker();

  static String makeAccessToken() {
    return AuthorizationServiceStub.faker.internet().uuid();
  }

  static JwtClaimsSet makeJwtClaimsSet() {
    long expiresIn = 500L;
    Instant now = Instant.now();

    return JwtClaimsSet.builder().issuer("project.zeta.test").expiresAt(now.plusSeconds(expiresIn))
        .issuedAt(now).build();
  }

  static Optional<UserEntity> makeUserEntity() {
    return Optional
        .of(UserEntity.builder().username(AuthorizationServiceStub.faker.internet().emailAddress())
            .password(AuthorizationServiceStub.faker.internet().password()).build());
  }

  static LoginUserResponse makeLoginUserResponse() {
    return makeLoginUserResponse("");
  }

  static LoginUserResponse makeLoginUserResponse(String accessToken) {
    var loginUserResponse = new LoginUserResponse();
    loginUserResponse.setAccessToken(accessToken);
    return loginUserResponse;
  }

  static LoginUserBodyRequest makeLoginUserBodyRequest() {
    return LoginUserBodyRequest.builder()
        .username(AuthorizationServiceStub.faker.internet().emailAddress())
        .password(AuthorizationServiceStub.faker.internet().password()).build();
  }
}
