package com.eric218110.project.zeta.domain.usecases.user;

import java.util.UUID;
import com.eric218110.project.zeta.domain.entities.user.UserEntity;

public interface LoadUserByUuid {
  UserEntity loadUserByUuid(UUID uuid);
}
