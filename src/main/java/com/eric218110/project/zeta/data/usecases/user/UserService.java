package com.eric218110.project.zeta.data.usecases.user;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.eric218110.project.zeta.data.entities.role.RoleEntity;
import com.eric218110.project.zeta.domain.enums.role.RoleEnum;
import com.eric218110.project.zeta.domain.http.user.AddUserRequestBody;
import com.eric218110.project.zeta.domain.http.user.AddUserResponse;
import com.eric218110.project.zeta.domain.usecases.user.AddUser;
import com.eric218110.project.zeta.infra.repositories.database.role.RoleRepository;
import com.eric218110.project.zeta.infra.repositories.database.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements AddUser {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Override
  public AddUserResponse onAddUser(AddUserRequestBody addUserRequestBody) {

    Optional<RoleEntity> loadRoleEntity = this.roleRepository.findByName(RoleEnum.USER.name());

    if (loadRoleEntity.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Not possible create user, role is not present");
    }

    return AddUserResponse.builder().build();

  }

}
