package com.eric218110.project.zeta.data.usecases.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.eric218110.project.zeta.data.provider.encoded.EncodedProvider;
import com.eric218110.project.zeta.domain.entities.user.UserEntity;
import com.eric218110.project.zeta.domain.http.user.AddUserRequestBody;
import com.eric218110.project.zeta.domain.http.user.AddUserResponse;
import com.eric218110.project.zeta.domain.usecases.validator.email.EmailValidator;
import com.eric218110.project.zeta.domain.usecases.validator.password.PasswordValidator;
import com.eric218110.project.zeta.infra.repositories.database.role.RoleRepository;
import com.eric218110.project.zeta.infra.repositories.database.user.UserRepository;

class UserServiceTests {

  @Mock
  private UserRepository userRepository;
  @Mock
  private RoleRepository roleRepository;
  @Mock
  private EncodedProvider encodedProvider;
  @Mock
  private PasswordValidator passwordValidator;
  @Mock
  private EmailValidator emailValidator;
  @Mock
  private ModelMapper modelMapper;

  @InjectMocks
  UserService userService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldOnCreateUserAndRoleIsNotFoundThrowsExceptionNotPossibleCreateUser() {
    AddUserRequestBody addUserRequestBody = UserServiceStub.makeAddUserRequestBody();
    when(this.passwordValidator.onValidatePassword(any())).thenReturn(true);
    when(this.emailValidator.onValidateEmail(any())).thenReturn(true);

    ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
        () -> userService.onAddUser(addUserRequestBody));

    assertEquals("Not possible create user, role is not present",
        responseStatusException.getReason());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseStatusException.getStatusCode());
  }

  // @Test
  // void shouldCallEncodeProvider() {
  // AddUserRequestBody addUserRequestBody = UserServiceStub.makeAddUserRequestBody();
  // AddUserResponse addUserResponse =
  // UserServiceStub.makeAddUserResponseBody(addUserRequestBody.getUsername());
  // UserEntity userEntity =
  // UserServiceStub.makeUserEntity(addUserRequestBody.getUsername(), addUserResponse.getUuid());
  // when(this.roleRepository.findByName(any())).thenReturn(UserServiceStub.makeRoleEntity());
  // when(this.roleRepository.findByName(any())).thenReturn(UserServiceStub.makeRoleEntity());
  // when(this.userRepository.save(any())).thenReturn(userEntity);
  // when(this.passwordValidator.onValidatePassword(any())).thenReturn(true);
  // when(this.emailValidator.onValidateEmail(any())).thenReturn(true);

  // userService.onAddUser(addUserRequestBody);

  // verify(this.encodedProvider, times(1)).onEncodeByValue(addUserRequestBody.getPassword());
  // }

  @Test
  void shouldOnAddUserThrowOnPasswordIsInvalid() {
    AddUserRequestBody addUserRequestBody = UserServiceStub.makeAddUserRequestBody();
    String hashedPasswordFake = UserServiceStub.makeHashPassword();
    AddUserResponse addUserResponse =
        UserServiceStub.makeAddUserResponseBody(addUserRequestBody.getUsername());
    UserEntity userEntity =
        UserServiceStub.makeUserEntity(addUserRequestBody.getUsername(), addUserResponse.getUuid());

    when(this.roleRepository.findByName(any())).thenReturn(UserServiceStub.makeRoleEntity());
    when(this.encodedProvider.onEncodeByValue(any())).thenReturn(hashedPasswordFake);
    when(this.userRepository.save(any())).thenReturn(userEntity);
    when(this.passwordValidator.onValidatePassword(any())).thenReturn(false);
    when(this.emailValidator.onValidateEmail(any())).thenReturn(true);

    ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
        () -> userService.onAddUser(addUserRequestBody));

    assertEquals("Not possible create user, password or email is invalid",
        responseStatusException.getReason());
    assertEquals(HttpStatus.BAD_REQUEST, responseStatusException.getStatusCode());
  }

  @Test
  void shouldOnAddUserThrowOnEmailIsInvalid() {
    AddUserRequestBody addUserRequestBody = UserServiceStub.makeAddUserRequestBody();
    String hashedPasswordFake = UserServiceStub.makeHashPassword();
    AddUserResponse addUserResponse =
        UserServiceStub.makeAddUserResponseBody(addUserRequestBody.getUsername());
    UserEntity userEntity =
        UserServiceStub.makeUserEntity(addUserRequestBody.getUsername(), addUserResponse.getUuid());

    when(this.roleRepository.findByName(any())).thenReturn(UserServiceStub.makeRoleEntity());
    when(this.encodedProvider.onEncodeByValue(any())).thenReturn(hashedPasswordFake);
    when(this.userRepository.save(any())).thenReturn(userEntity);
    when(this.passwordValidator.onValidatePassword(any())).thenReturn(true);
    when(this.emailValidator.onValidateEmail(any())).thenReturn(false);

    ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
        () -> userService.onAddUser(addUserRequestBody));

    assertEquals("Not possible create user, password or email is invalid",
        responseStatusException.getReason());
    assertEquals(HttpStatus.BAD_REQUEST, responseStatusException.getStatusCode());
  }

  // @Test
  // void shouldReturnAddUserResponse() {
  // AddUserRequestBody addUserRequestBody = UserServiceStub.makeAddUserRequestBody();
  // String hashedPasswordFake = UserServiceStub.makeHashPassword();
  // AddUserResponse addUserResponse =
  // UserServiceStub.makeAddUserResponseBody(addUserRequestBody.getUsername());
  // UserEntity userEntity =
  // UserServiceStub.makeUserEntity(addUserRequestBody.getUsername(), addUserResponse.getUuid());

  // when(this.roleRepository.findByName(any())).thenReturn(UserServiceStub.makeRoleEntity());
  // when(this.encodedProvider.onEncodeByValue(any())).thenReturn(hashedPasswordFake);
  // when(this.userRepository.save(any())).thenReturn(userEntity);
  // when(this.passwordValidator.onValidatePassword(any())).thenReturn(true);
  // when(this.emailValidator.onValidateEmail(any())).thenReturn(true);

  // AddUserResponse response = userService.onAddUser(addUserRequestBody);
  // assertEquals(response, addUserResponse);
  // }
}
