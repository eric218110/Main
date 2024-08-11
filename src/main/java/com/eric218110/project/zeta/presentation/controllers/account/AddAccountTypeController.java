package com.eric218110.project.zeta.presentation.controllers.account;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eric218110.project.zeta.domain.http.account.AddAccountRequest;
import com.eric218110.project.zeta.domain.http.account.ShowAccountResponse;
import com.eric218110.project.zeta.domain.usecases.account.AddOneAccount;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("account")
public class AddAccountTypeController {

  private final AddOneAccount addOneAccount;

  @PostMapping
  public ResponseEntity<ShowAccountResponse> save(
      @Valid @RequestBody AddAccountRequest addAccountDto,
      JwtAuthenticationToken jwtAuthenticationToken) {

    UUID userUuid = UUID.fromString(jwtAuthenticationToken.getName());
    ShowAccountResponse addAccount = this.addOneAccount.addAccount(addAccountDto, userUuid);

    return ResponseEntity.status(HttpStatus.CREATED).body(addAccount);
  }

}
