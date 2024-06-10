package com.eric218110.project.zeta.infra.providers.encoded.token;

import java.time.Instant;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.stereotype.Service;
import com.eric218110.project.zeta.data.entities.user.UserEntity;
import com.eric218110.project.zeta.data.provider.token.TokenProvider;

@Service
public class JWTTokenProvider implements TokenProvider<JwtClaimsSet> {

  @Override
  public JwtClaimsSet onGenerateTokenClaimsByUserEntity(UserEntity userEntity) {

    long expiresIn = 500L;
    Instant now = Instant.now();

    return JwtClaimsSet.builder().issuer("project.zeta").subject(userEntity.getUserId().toString())
        .expiresAt(now.plusSeconds(expiresIn)).issuedAt(now).build();
  }
}
