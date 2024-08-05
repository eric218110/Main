package com.eric218110.project.zeta.domain.usecases.account;

import com.eric218110.project.zeta.domain.entities.account.AccountEntity;

public interface ShowAccountByAccountIdAndUserId {
  AccountEntity showAccountByAccountIdAndUserId(String accountId, String userId);
}
