package com.eric218110.project.zeta.domain.enums.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoleEnum {
  ADMIN(1L),
  SYSTEM(2L),
  USER(3L);

  private final long roleId;

}
