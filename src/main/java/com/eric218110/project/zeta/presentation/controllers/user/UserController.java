package com.eric218110.project.zeta.presentation.controllers.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.eric218110.project.zeta.domain.http.user.AddUserRequestBody;
import com.eric218110.project.zeta.domain.http.user.AddUserResponse;
import com.eric218110.project.zeta.domain.usecases.user.AddUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController("user")
public class UserController {

  private final AddUser addUserService;


  @PostMapping("create")
  ResponseEntity<AddUserResponse> addUser(
      @RequestBody @Valid AddUserRequestBody addUserRequestBody) {

    return ResponseEntity.ok(this.addUserService.onAddUser(addUserRequestBody));
  }
}
