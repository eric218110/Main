package com.eric218110.project.zeta.data.usecases.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.eric218110.project.zeta.data.entities.user.UserEntity;
import com.eric218110.project.zeta.data.provider.encoded.EncodedProvider;
import com.eric218110.project.zeta.domain.http.user.AddUserRequestBody;
import com.eric218110.project.zeta.domain.http.user.AddUserResponse;
import com.eric218110.project.zeta.infra.repositories.database.role.RoleRepository;
import com.eric218110.project.zeta.infra.repositories.database.user.UserRepository;

class UserServiceTests {

  @Mock
  private UserRepository userRepository;
  @Mock
  private RoleRepository roleRepository;
  @Mock
  private EncodedProvider encodedProvider;

  @InjectMocks
  UserService userService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldOnCreateUserAndRoleIsNotFoundThrowsExceptionNotPossibleCreateUser() {
    AddUserRequestBody addUserRequestBody = UserServiceStub.makeAddUserRequestBody();

    ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
        () -> userService.onAddUser(addUserRequestBody));

    assertEquals("Not possible create user, role is not present",
        responseStatusException.getReason());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseStatusException.getStatusCode());
  }

  @Test
  void shouldCallEncodeProvider() {
    AddUserRequestBody addUserRequestBody = UserServiceStub.makeAddUserRequestBody();
    when(this.roleRepository.findByName(any())).thenReturn(UserServiceStub.makeRoleEntity());

    userService.onAddUser(addUserRequestBody);

    verify(this.encodedProvider, times(1)).onEncodeByValue(addUserRequestBody.getPassword());
  }

  @Test
  void shouldReturnAddUserResponse() {
    AddUserRequestBody addUserRequestBody = UserServiceStub.makeAddUserRequestBody();
    String hashedPasswordFake = UserServiceStub.makeHashPassword();
    AddUserResponse addUserResponse =
        UserServiceStub.makeAddUserResponseBody(addUserRequestBody.getUsername());
    UserEntity userEntity =
        UserServiceStub.makeUserEntity(addUserRequestBody.getUsername(), addUserResponse.getUuid());

    when(this.roleRepository.findByName(any())).thenReturn(UserServiceStub.makeRoleEntity());
    when(this.encodedProvider.onEncodeByValue(any())).thenReturn(hashedPasswordFake);
    when(this.userRepository.save(any())).thenReturn(userEntity);

    AddUserResponse response = userService.onAddUser(addUserRequestBody);
    assertEquals(response, addUserResponse);

  }
}
