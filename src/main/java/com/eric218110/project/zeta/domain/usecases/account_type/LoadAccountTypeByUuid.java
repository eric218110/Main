package com.eric218110.project.zeta.domain.usecases.account_type;

import com.eric218110.project.zeta.domain.entities.account_type.AccountTypeEntity;

public interface LoadAccountTypeByUuid {
  AccountTypeEntity loadAccountTypeByUuid(String uuid);
}
