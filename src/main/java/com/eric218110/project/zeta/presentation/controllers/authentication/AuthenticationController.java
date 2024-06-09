package com.eric218110.project.zeta.presentation.controllers.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eric218110.project.zeta.domain.http.login.LoginUserBodyRequest;
import com.eric218110.project.zeta.domain.http.login.LoginUserResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

  @PostMapping("/login")
  public ResponseEntity<LoginUserResponse> loginUser(@RequestBody @Valid LoginUserBodyRequest loginUserBody) {
    return ResponseEntity.ok(new LoginUserResponse(""));
  }

}
