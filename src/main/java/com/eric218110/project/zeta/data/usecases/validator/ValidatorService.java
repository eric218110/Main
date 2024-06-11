package com.eric218110.project.zeta.data.usecases.validator;

import org.springframework.stereotype.Service;
import com.eric218110.project.zeta.domain.usecases.validator.email.EmailValidator;
import com.eric218110.project.zeta.domain.usecases.validator.password.PasswordValidator;

@Service
public class ValidatorService implements PasswordValidator, EmailValidator {

  @Override
  public boolean onValidatePassword(String password) {
    String regex = "^(?=.*[!@#$%&*()])(?=.*\\d).{8,}$";

    return password.matches(regex);
  }

  @Override
  public boolean onValidateEmail(String email) {
    String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    return email.matches(regex);
  }

}
