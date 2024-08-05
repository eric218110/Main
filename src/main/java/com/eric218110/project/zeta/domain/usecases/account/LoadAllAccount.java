package com.eric218110.project.zeta.domain.usecases.account;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.eric218110.project.zeta.domain.http.account.ShowAccountResponse;

public interface LoadAllAccount {
  Page<ShowAccountResponse> listAllByUserUuid(UUID userUuid, Pageable pageable);
}
