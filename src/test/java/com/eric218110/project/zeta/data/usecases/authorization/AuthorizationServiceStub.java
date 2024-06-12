package com.eric218110.project.zeta.data.usecases.authorization;

import java.time.Instant;
import java.util.Optional;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import com.eric218110.project.zeta.domain.entities.user.UserEntity;
import com.eric218110.project.zeta.domain.http.login.LoginUserBodyRequest;
import com.eric218110.project.zeta.domain.http.login.LoginUserResponse;
import com.github.javafaker.Faker;

public class AuthorizationServiceStub {
  static public Faker faker = new Faker();

  static public String makeAccessToken() {
    return AuthorizationServiceStub.faker.internet().uuid();
  }

  static public JwtClaimsSet makeJwtClaimsSet() {
    long expiresIn = 500L;
    Instant now = Instant.now();

    return JwtClaimsSet.builder().issuer("project.zeta.test").expiresAt(now.plusSeconds(expiresIn))
        .issuedAt(now).build();
  }

  static public Optional<UserEntity> makeUserEntity() {
    return Optional
        .of(UserEntity.builder().username(AuthorizationServiceStub.faker.internet().emailAddress())
            .password(AuthorizationServiceStub.faker.internet().password()).build());
  }

  static public LoginUserResponse makeLoginUserResponse() {
    return LoginUserResponse.builder().accessToken("").build();
  }

  static public LoginUserResponse makeLoginUserResponse(String accessToken) {
    return LoginUserResponse.builder().accessToken(accessToken).build();
  }

  static public LoginUserBodyRequest makeLoginUserBodyRequest() {
    return LoginUserBodyRequest.builder()
        .username(AuthorizationServiceStub.faker.internet().emailAddress())
        .password(AuthorizationServiceStub.faker.internet().password()).build();
  }
}
