package com.eric218110.project.zeta.data.usecases.account;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.eric218110.project.zeta.domain.entities.account.AccountEntity;
import com.eric218110.project.zeta.domain.http.account.AddAccountRequest;
import com.eric218110.project.zeta.domain.http.account.ShowAccountResponse;
import com.eric218110.project.zeta.domain.usecases.account.AddOneAccount;
import com.eric218110.project.zeta.domain.usecases.account.LoadAllAccount;
import com.eric218110.project.zeta.domain.usecases.account.ShowAccountByAccountIdAndUserId;
import com.eric218110.project.zeta.domain.usecases.account_type.LoadAccountTypeByUuid;
import com.eric218110.project.zeta.domain.usecases.color.LoadColorByUuid;
import com.eric218110.project.zeta.domain.usecases.institution.LoadInstitutionByUuid;
import com.eric218110.project.zeta.domain.usecases.user.LoadUserByUuid;
import com.eric218110.project.zeta.infra.repositories.database.account.AccountRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountService
    implements LoadAllAccount, AddOneAccount, ShowAccountByAccountIdAndUserId {
  private final LoadUserByUuid loadUserByUuidService;
  private final LoadColorByUuid loadColorByUuidService;
  private final LoadInstitutionByUuid loadInstructionService;
  private final AccountRepository accountRepository;
  private final LoadAccountTypeByUuid accountTypeService;

  @Override
  public ShowAccountResponse addAccount(AddAccountRequest addCardDto, UUID userUuid) {
    var user = this.loadUserByUuidService.loadUserByUuid(userUuid);
    var color = this.loadColorByUuidService.loadColorByUuid(addCardDto.getColorId());
    var background = this.loadColorByUuidService.loadColorByUuid(addCardDto.getBackgroundId());
    var institution =
        this.loadInstructionService.loadInstitutionByUuid(addCardDto.getInstitutionId());
    var accountType = this.accountTypeService.loadAccountTypeByUuid(addCardDto.getAccountTypeId());

    var accountEntity = AccountEntity.builder().description(addCardDto.getDescription()).user(user)
        .balance(addCardDto.getBalance()).color(color).institution(institution).type(accountType)
        .background(background).build();

    var accountToSave = this.accountRepository.save(accountEntity);

    return ShowAccountResponse.builder().description(accountToSave.getDescription())
        .balance(accountToSave.getBalance()).uuid(accountToSave.getUuid())
        .type(accountToSave.getType()).color(accountToSave.getColor())
        .background(accountToSave.getBackground()).institution(accountToSave.getInstitution())
        .creationTimestamp(accountToSave.getCreationTimestamp()).build();
  }

  @Override
  public Page<ShowAccountResponse> listAllByUserUuid(UUID userUuid, Pageable pageable) {
    var user = this.loadUserByUuidService.loadUserByUuid(userUuid);
    var accounts = this.accountRepository.findByUser(user, pageable);

    return accounts.map(this::entityToDto);
  }

  private ShowAccountResponse entityToDto(AccountEntity accountEntity) {
    return ShowAccountResponse.builder().description(accountEntity.getDescription())
        .balance(accountEntity.getBalance()).uuid(accountEntity.getUuid())
        .type(accountEntity.getType()).color(accountEntity.getColor())
        .institution(accountEntity.getInstitution()).background(accountEntity.getBackground())
        .creationTimestamp(accountEntity.getCreationTimestamp()).build();
  }

  @Override
  public AccountEntity showAccountByAccountIdAndUserId(String accountId, String userId) {
    var user = this.loadUserByUuidService.loadUserByUuid(UUID.fromString(userId));
    var account = this.loadAccountByUuid(accountId);

    if (!user.getUserId().equals(account.getUser().getUserId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Not permission for load this account");
    }

    return account;
  }

  public AccountEntity loadAccountByUuid(String uuid) {
    var account = this.accountRepository.findById(UUID.fromString(uuid));

    if (account.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found account");
    }

    return account.get();
  }
}
