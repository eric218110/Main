package com.eric218110.project.zeta.data.usecases.user;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.eric218110.project.zeta.data.provider.encoded.EncodedProvider;
import com.eric218110.project.zeta.domain.entities.role.RoleEntity;
import com.eric218110.project.zeta.domain.entities.user.UserEntity;
import com.eric218110.project.zeta.domain.enums.role.RoleEnum;
import com.eric218110.project.zeta.domain.http.user.AddUserRequestBody;
import com.eric218110.project.zeta.domain.http.user.AddUserResponse;
import com.eric218110.project.zeta.domain.usecases.user.AddUser;
import com.eric218110.project.zeta.domain.usecases.validator.email.EmailValidator;
import com.eric218110.project.zeta.domain.usecases.validator.password.PasswordValidator;
import com.eric218110.project.zeta.infra.repositories.database.role.RoleRepository;
import com.eric218110.project.zeta.infra.repositories.database.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements AddUser {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final EncodedProvider encodedProvider;
  private final PasswordValidator passwordValidator;
  private final EmailValidator emailValidator;
  private final ModelMapper modelMapper;

  @Override
  public AddUserResponse onAddUser(AddUserRequestBody addUserRequestBody) {

    var passwordIsValid =
        this.passwordValidator.onValidatePassword(addUserRequestBody.getPassword());

    var emailIsValid = this.emailValidator.onValidateEmail(addUserRequestBody.getUsername());

    if (!passwordIsValid || !emailIsValid) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Not possible create user, password or email is invalid");
    }

    Optional<RoleEntity> loadRoleEntity = this.roleRepository.findByName(RoleEnum.USER.name());
    Optional<UserEntity> loadUserByUsername =
        this.userRepository.findByUsername(addUserRequestBody.getUsername());

    if (loadRoleEntity.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Not possible create user, role is not present");
    }

    if (loadUserByUsername.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Not possible create user, user already exists");
    }

    Set<RoleEntity> roles = new HashSet<>();
    roles.add(loadRoleEntity.get());

    String passwordEncoded = this.encodedProvider.onEncodeByValue(addUserRequestBody.getPassword());

    UserEntity userEntityToSave = modelMapper.map(addUserRequestBody, UserEntity.class);
    userEntityToSave.setPassword(passwordEncoded);
    userEntityToSave.setRoles(roles);

    UserEntity userSaved = this.userRepository.save(userEntityToSave);

    List<String> userRoles =
        userEntityToSave.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toList());

    return AddUserResponse.builder().uuid(userSaved.getUserId()).username(userSaved.getUsername())
        .roles(userRoles).build();

  }

}
