package com.eric218110.project.zeta.data.provider.token;

import com.eric218110.project.zeta.data.entities.user.UserEntity;

public interface TokenProvider<T> {
  T onGenerateTokenClaimsByUserEntity(UserEntity userEntity);
}
