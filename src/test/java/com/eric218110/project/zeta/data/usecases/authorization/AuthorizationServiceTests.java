package com.eric218110.project.zeta.data.usecases.authorization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.web.server.ResponseStatusException;
import com.eric218110.project.zeta.data.provider.encoded.EncodedProvider;
import com.eric218110.project.zeta.data.provider.token.TokenProvider;
import com.eric218110.project.zeta.domain.http.login.LoginUserBodyRequest;
import com.eric218110.project.zeta.infra.repositories.database.user.UserRepository;
import com.github.javafaker.Faker;

class AuthorizationServiceTests {
  private Faker faker = new Faker();

  @Mock
  private UserRepository userRepository;
  @Mock
  private EncodedProvider encodedProvider;
  @Mock
  private TokenProvider<JwtClaimsSet> tokenProvider;

  @InjectMocks
  AuthorizationService authorizationService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnAccessTokenOnAuthorizeUserIsSuccess() {
    LoginUserBodyRequest loginUserBodyRequest = AuthorizationServiceStub.makeLoginUserBodyRequest();
    String accessTokenFake = AuthorizationServiceStub.makeAccessToken();
    when(this.userRepository.findByUsername(anyString()))
        .thenReturn(AuthorizationServiceStub.makeUserEntity());
    when(this.encodedProvider.valueEncodedMath(anyString(), anyString())).thenReturn(true);
    when(this.encodedProvider.generateTokenValueByClaims(any(), any(), any()))
        .thenReturn(accessTokenFake);
    when(this.tokenProvider.onGenerateTokenClaimsByUserEntity(any()))
        .thenReturn(AuthorizationServiceStub.makeJwtClaimsSet());

    var result = authorizationService.onAuthorizeUser(loginUserBodyRequest);

    assertEquals(result, AuthorizationServiceStub.makeLoginUserResponse(accessTokenFake));
  }

  @Test
  void shouldThrowsOnAuthorizeUserOnUserNotExist() {
    LoginUserBodyRequest loginUserBodyRequest = AuthorizationServiceStub.makeLoginUserBodyRequest();

    ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
        () -> authorizationService.onAuthorizeUser(loginUserBodyRequest));

    assertEquals("Username or password is invalid", responseStatusException.getReason());
  }

  @Test
  void shouldThrowsOnAuthorizeUserOnPasswordIsIncorrect() {
    LoginUserBodyRequest loginUserBodyRequest = AuthorizationServiceStub.makeLoginUserBodyRequest();
    when(this.userRepository.findByUsername(anyString()))
        .thenReturn(AuthorizationServiceStub.makeUserEntity());
    when(this.encodedProvider.valueEncodedMath(anyString(), anyString())).thenReturn(false);

    ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
        () -> authorizationService.onAuthorizeUser(loginUserBodyRequest));

    assertEquals("Username or password is invalid", responseStatusException.getReason());
  }

}
