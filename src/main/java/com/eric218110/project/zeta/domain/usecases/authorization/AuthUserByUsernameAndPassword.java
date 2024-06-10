package com.eric218110.project.zeta.domain.usecases.authorization;

import com.eric218110.project.zeta.domain.http.login.LoginUserBodyRequest;
import com.eric218110.project.zeta.domain.http.login.LoginUserResponse;

public interface AuthUserByUsernameAndPassword {
  LoginUserResponse onAuthorizeUser(LoginUserBodyRequest loginUserBodyRequest);
}
