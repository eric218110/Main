package com.eric218110.project.zeta.data.provider.encoded;

public interface DecodedProvider {
  <T> String generateTokenValueByClaims(T claims);
}
