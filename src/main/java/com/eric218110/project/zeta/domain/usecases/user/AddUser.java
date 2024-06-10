package com.eric218110.project.zeta.domain.usecases.user;

import com.eric218110.project.zeta.domain.http.user.AddUserRequestBody;
import com.eric218110.project.zeta.domain.http.user.AddUserResponse;

public interface AddUser {
  AddUserResponse onAddUser(AddUserRequestBody addUserRequestBody);
}
