package com.eric218110.project.zeta.data.provider.encoded;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;

public interface EncodedProvider {
  String onEncodeByValue(String value);

  String generateTokenValueByClaims(JwtClaimsSet claims, RSAPublicKey publicKey, RSAPrivateKey privateKey);

  boolean valueEncodedMath(String rawValue, String encodedValue);
}