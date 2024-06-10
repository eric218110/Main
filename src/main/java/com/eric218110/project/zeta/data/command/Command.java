package com.eric218110.project.zeta.data.command;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import com.eric218110.project.zeta.data.entities.role.RoleEntity;
import com.eric218110.project.zeta.data.entities.user.UserEntity;
import com.eric218110.project.zeta.data.provider.encoded.EncodedProvider;
import com.eric218110.project.zeta.domain.enums.role.RoleEnum;
import com.eric218110.project.zeta.infra.repositories.database.role.RoleRepository;
import com.eric218110.project.zeta.infra.repositories.database.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class Command implements CommandLineRunner {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final EncodedProvider encodedProvider;

  @Value("${security.jwt.key.public}")
  private RSAPublicKey rsaPublicKey;
  @Value("${security.jwt.key.private}")
  private RSAPrivateKey rsaPrivateKey;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    RoleEntity roleEntity = RoleEntity.builder().name(RoleEnum.ADMIN.name()).build();

    String password = this.encodedProvider.onEncodeByValue("123");
    UserEntity userEntity = UserEntity.builder().username("admin").password(password).build();

    var role = this.roleRepository.findByName(RoleEnum.ADMIN.name());
    var userAdmin = this.userRepository.findByUsername(userEntity.getUsername());

    role.ifPresentOrElse(roleIsPresent -> log.info("skip create role ADIM"),
        () -> this.roleRepository.save(roleEntity));

    userAdmin.ifPresentOrElse(userIsPresent -> log.info("skip create User Admin"),
        () -> this.userRepository.save(userEntity));

  }

}
