package com.eric218110.project.zeta.data.command;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import com.eric218110.project.zeta.data.provider.encoded.EncodedProvider;
import com.eric218110.project.zeta.domain.entities.institutions.InstitutionsEntity;
import com.eric218110.project.zeta.domain.entities.role.RoleEntity;
import com.eric218110.project.zeta.domain.entities.user.UserEntity;
import com.eric218110.project.zeta.domain.enums.role.RoleEnum;
import com.eric218110.project.zeta.infra.http.providers.LoadAllBanksProvider;
import com.eric218110.project.zeta.infra.http.response.BanksResponse;
import com.eric218110.project.zeta.infra.repositories.database.institution.InstitutionRepository;
import com.eric218110.project.zeta.infra.repositories.database.role.RoleRepository;
import com.eric218110.project.zeta.infra.repositories.database.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class Command implements CommandLineRunner {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final EncodedProvider encodedProvider;
  private final LoadAllBanksProvider loadAllBanksProvider;
  private final InstitutionRepository institutionRepository;

  @Value("${security.jwt.key.public}")
  private RSAPublicKey rsaPublicKey;
  @Value("${security.jwt.key.private}")
  private RSAPrivateKey rsaPrivateKey;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    RoleEntity roleAdmin = RoleEntity.builder().name(RoleEnum.ADMIN.name()).build();
    RoleEntity roleUser = RoleEntity.builder().name(RoleEnum.USER.name()).build();

    String password = this.encodedProvider.onEncodeByValue("123");
    UserEntity userEntity = UserEntity.builder().username("admin").password(password).build();

    var loadRoleAdmin = this.roleRepository.findByName(RoleEnum.ADMIN.name());
    var loadRoleUser = this.roleRepository.findByName(RoleEnum.USER.name());
    var userAdmin = this.userRepository.findByUsername(userEntity.getUsername());

    loadRoleAdmin.ifPresentOrElse(roleIsPresent -> {
    }, () -> this.roleRepository.save(roleAdmin));
    loadRoleUser.ifPresentOrElse(roleIsPresent -> {
    }, () -> this.roleRepository.save(roleUser));

    userAdmin.ifPresentOrElse(userIsPresent -> {
    }, () -> this.userRepository.save(userEntity));

    var banks = this.loadAllBanksProvider.getAllBanks();

    banks.stream().forEach(bank -> {
      var alreadyExists = institutionRepository.findByIspb(bank.getIspb());

      if (alreadyExists == null) {
        var institution = this.responseToEntity(bank);
        institutionRepository.save(institution);
      }
    });

  }

  InstitutionsEntity responseToEntity(BanksResponse banksResponse) {
    var code = banksResponse.getCode() == null ? Integer.valueOf(-1) : banksResponse.getCode();

    return InstitutionsEntity.builder().ispb(banksResponse.getIspb()).name(banksResponse.getName())
        .code(code).fullName(banksResponse.getFullName()).build();
  }

}
