package com.eric218110.project.zeta.domain.usecases.account_type;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.eric218110.project.zeta.domain.http.account_type.ShowAccountTypeResponse;

public interface LoadAllAccountType {
  Page<ShowAccountTypeResponse> listAllByUserUuid(Pageable pageable);
}
