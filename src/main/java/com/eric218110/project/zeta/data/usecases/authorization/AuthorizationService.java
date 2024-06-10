package com.eric218110.project.zeta.data.usecases.authorization;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;

import com.eric218110.project.zeta.data.entities.user.UserEntity;
import com.eric218110.project.zeta.data.provider.encoded.EncodedProvider;
import com.eric218110.project.zeta.data.provider.token.TokenProvider;
import com.eric218110.project.zeta.domain.http.login.LoginUserBodyRequest;
import com.eric218110.project.zeta.domain.http.login.LoginUserResponse;
import com.eric218110.project.zeta.domain.usecases.authorization.AuthUserByUsernameAndPassword;
import com.eric218110.project.zeta.infra.repositories.database.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationService implements AuthUserByUsernameAndPassword {

  private final UserRepository userRepository;
  private final EncodedProvider encodedProvider;
  private final TokenProvider<JwtClaimsSet> tokenProvider;

  @Value("${security.jwt.key.public}")
  private RSAPublicKey rsaPublicKey;
  @Value("${security.jwt.key.private}")
  private RSAPrivateKey rsaPrivateKey;

  @Override
  public LoginUserResponse onAuthorizeUser(LoginUserBodyRequest loginUserBodyRequest) {

    Optional<UserEntity> userEntityOptional = this.userRepository.findByUsername(loginUserBodyRequest.getUsername());

    if (userEntityOptional.isEmpty()) {
      throw new BadCredentialsException("Username or password is invalid");
    }

    UserEntity userEntity = userEntityOptional.get();
    boolean passwordIsMatched = this.encodedProvider.valueEncodedMath(
        loginUserBodyRequest.getPassword(),
        userEntity.getPassword());

    if (!passwordIsMatched) {
      throw new BadCredentialsException("Username or password is invalid");
    }

    var tokenClaims = this.tokenProvider.onGenerateTokenClaimsByUserEntity(userEntity);

    String accessToken = this.encodedProvider.generateTokenValueByClaims(tokenClaims, this.rsaPublicKey,
        this.rsaPrivateKey);

    return LoginUserResponse.builder().accessToken(accessToken).build();
  }

}
