package com.eric218110.project.zeta.data.usecases.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.eric218110.project.zeta.domain.http.user.AddUserRequestBody;
import com.eric218110.project.zeta.infra.repositories.database.role.RoleRepository;
import com.eric218110.project.zeta.infra.repositories.database.user.UserRepository;

class UserServiceTests {

  @Mock
  private UserRepository userRepository;
  @Mock
  private RoleRepository roleRepository;

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

}
