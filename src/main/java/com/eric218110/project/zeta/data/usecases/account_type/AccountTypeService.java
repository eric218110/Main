package com.eric218110.project.zeta.data.usecases.account_type;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.eric218110.project.zeta.domain.entities.account_type.AccountTypeEntity;
import com.eric218110.project.zeta.domain.http.account_type.ShowAccountTypeResponse;
import com.eric218110.project.zeta.domain.usecases.account_type.LoadAccountTypeByUuid;
import com.eric218110.project.zeta.domain.usecases.account_type.LoadAllAccountType;
import com.eric218110.project.zeta.infra.repositories.database.account_type.AccountTypeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountTypeService implements LoadAllAccountType, LoadAccountTypeByUuid {
  private final AccountTypeRepository accountTypeRepository;

  @Override
  public Page<ShowAccountTypeResponse> listAllByUserUuid(Pageable pageable) {

    var sortedByName = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
        Sort.by(Sort.Direction.ASC, "name"));

    var result = this.accountTypeRepository.findAll(sortedByName);

    return result.map(account -> ShowAccountTypeResponse.builder().uuid(account.getUuid())
        .name(account.getName()).build());
  }

  @Override
  public AccountTypeEntity loadAccountTypeByUuid(String uuid) {
    var accountType = this.accountTypeRepository.findById(UUID.fromString(uuid));

    if (accountType.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found Account Type");
    }

    return accountType.get();
  }
}
