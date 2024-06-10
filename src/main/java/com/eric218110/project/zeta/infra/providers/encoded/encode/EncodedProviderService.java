package com.eric218110.project.zeta.infra.providers.encoded.encode;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.stereotype.Service;

import com.eric218110.project.zeta.data.provider.encoded.EncodedProvider;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.proc.SecurityContext;

@Service
public class EncodedProviderService implements EncodedProvider {

  private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

  @Override
  public boolean valueEncodedMath(String rawValue, String encodedValue) {
    return this.bCryptPasswordEncoder.matches(rawValue, encodedValue);
  }

  @Override
  public String generateTokenValueByClaims(JwtClaimsSet claims, RSAPublicKey publicKey, RSAPrivateKey privateKey) {

    JWK jwk = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
    ImmutableJWKSet<SecurityContext> jwkSet = new ImmutableJWKSet<>(new JWKSet(jwk));

    var nimbusJwtEncoder = new NimbusJwtEncoder(jwkSet);

    if (claims instanceof JwtClaimsSet) {
      return nimbusJwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    return "";
  }

  @Override
  public String onEncodeByValue(String value) {
    return this.bCryptPasswordEncoder.encode(value);
  }
}
