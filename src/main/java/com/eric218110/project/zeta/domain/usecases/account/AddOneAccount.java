package com.eric218110.project.zeta.domain.usecases.account;

import java.util.UUID;
import com.eric218110.project.zeta.domain.http.account.AddAccountRequest;
import com.eric218110.project.zeta.domain.http.account.ShowAccountResponse;

public interface AddOneAccount {
  ShowAccountResponse addAccount(AddAccountRequest addCardDto, UUID userUuid);
}
