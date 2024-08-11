package com.eric218110.project.zeta.data.command;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import com.eric218110.project.zeta.data.provider.encoded.EncodedProvider;
import com.eric218110.project.zeta.domain.entities.account_type.AccountTypeEntity;
import com.eric218110.project.zeta.domain.entities.colors.ColorsEntity;
import com.eric218110.project.zeta.domain.entities.institutions.InstitutionsEntity;
import com.eric218110.project.zeta.domain.entities.role.RoleEntity;
import com.eric218110.project.zeta.domain.entities.user.UserEntity;
import com.eric218110.project.zeta.domain.enums.role.RoleEnum;
import com.eric218110.project.zeta.infra.http.providers.LoadAllBanksProvider;
import com.eric218110.project.zeta.infra.http.response.BanksResponse;
import com.eric218110.project.zeta.infra.repositories.database.account_type.AccountTypeRepository;
import com.eric218110.project.zeta.infra.repositories.database.color.ColorRepository;
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
  private final ColorRepository colorRepository;
  private final AccountTypeRepository accountTypeRepository;

  @Value("${security.jwt.key.public}")
  private RSAPublicKey rsaPublicKey;
  @Value("${security.jwt.key.private}")
  private RSAPrivateKey rsaPrivateKey;
  @Value("${application.features.run.seed}")
  private boolean enableRunSeed;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    if (this.enableRunSeed) {
      this.insertUser();
      this.insertColors();
      this.insertInstitutions();
      this.addAccountTypes();
    }
  }

  private void addAccountTypes() {
    var accountTypeList = List.of(
        AccountTypeEntity.builder().name("Current account").key("current_account").build(),
        AccountTypeEntity.builder().name("Savings account").key("savings_account").build(),
        AccountTypeEntity.builder().name("Payment account").key("payment_account").build(),
        AccountTypeEntity.builder().name("Salary account").key("salary_account").build(),
        AccountTypeEntity.builder().name("University account").key("university_account").build(),
        AccountTypeEntity.builder().name("Digital account").key("digital_account").build(),
        AccountTypeEntity.builder().name("Account for minors").key("Account_for_minors").build(),
        AccountTypeEntity.builder().name("Joint account").key("joint_account").build(),
        AccountTypeEntity.builder().name("Bitcoin wallet").key("bitcoin_wallet").build());

    accountTypeList.forEach(accountType -> {
      var accountTypeAlreadyExists = this.accountTypeRepository.findByKey(accountType.getKey());

      if (accountTypeAlreadyExists.isEmpty()) {
        this.accountTypeRepository.save(accountType);
      }
    });
  }

  private void insertUser() {
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

  }

  private void insertInstitutions() {
    var banks = this.loadAllBanksProvider.getAllBanks();

    banks.stream().forEach(bank -> {
      var alreadyExists = institutionRepository.findByIspb(bank.getIspb());

      if (alreadyExists == null) {
        var institution = this.responseToEntity(bank);
        institutionRepository.save(institution);
      }
    });
  }

  private InstitutionsEntity responseToEntity(BanksResponse banksResponse) {
    var code = banksResponse.getCode() == null ? Integer.valueOf(-1) : banksResponse.getCode();

    return InstitutionsEntity.builder().ispb(banksResponse.getIspb()).name(banksResponse.getName())
        .code(code).fullName(banksResponse.getFullName()).build();
  }

  private void insertColors() {
    var colors = List.of(
        ColorsEntity.builder().description("dark Surface").name("darkSurface").argb("0xFF0e0e12")
            .hex("#0e0e12").build(),
        ColorsEntity.builder().description("pitch Black").name("pitchBlack").argb("0xFF000000")
            .hex("#000000").build(),
        ColorsEntity.builder().description("dark Gray").name("darkGray").argb("0xFF353542")
            .hex("#353542").build(),
        ColorsEntity.builder().description("slate Gray").name("slateGray").argb("0xFF303746")
            .hex("#303746").build(),
        ColorsEntity.builder().description("charcoal Gray").name("charcoalGray").argb("0xFF4e4e61")
            .hex("#4e4e61").build(),
        ColorsEntity.builder().description("medium Gray").name("mediumGray").argb("0xFF666680")
            .hex("#666680").build(),
        ColorsEntity.builder().description("light Gray").name("lightGray").argb("0xFF83839c")
            .hex("#83839c").build(),
        ColorsEntity.builder().description("lighter Gray").name("lighterGray").argb("0xFFa2a2b5")
            .hex("#a2a2b5").build(),
        ColorsEntity.builder().description("very Light Gray").name("veryLightGray")
            .argb("0xFFc1c1cd").hex("#c1c1cd").build(),
        ColorsEntity.builder().description("near White").name("nearWhite").argb("0xFFe0e0e6")
            .hex("#e0e0e6").build(),
        ColorsEntity.builder().description("pure White").name("pureWhite").argb("0xFFffffff")
            .hex("#ffffff").build(),
        ColorsEntity.builder().description("pure Black").name("pureBlack").argb("0xFF101013")
            .hex("#101013").build(),
        ColorsEntity.builder().description("deep Purple").name("deepPurple").argb("0xFF7768D8")
            .hex("#7768D8").build(),
        ColorsEntity.builder().description("deep Purple Disabled").name("deepPurpleDisabled")
            .argb("0xFF7768D8").hex("#7768D8").build(),
        ColorsEntity.builder().description("vibrant Purple").name("vibrantPurple")
            .argb("0xFF7722ff").hex("#7722ff").build(),
        ColorsEntity.builder().description("bright Purple").name("brightPurple").argb("0xFF924eff")
            .hex("#924eff").build(),
        ColorsEntity.builder().description("soft Purple").name("softPurple").argb("0xFFad7bff")
            .hex("#ad7bff").build(),
        ColorsEntity.builder().description("pale Purple").name("palePurple").argb("0xFFc9a7ff")
            .hex("#c9a7ff").build(),
        ColorsEntity.builder().description("light Purple").name("lightPurple").argb("0xFFe4d3ff")
            .hex("#e4d3ff").build(),
        ColorsEntity.builder().description("bold Red").name("boldRed").argb("0xFFFD615B")
            .hex("#FD615B").build(),
        ColorsEntity.builder().description("soft Red").name("softRed").argb("0xFFffa699")
            .hex("#ffa699").build(),
        ColorsEntity.builder().description("pale Red").name("paleRed").argb("0xFFffd2cc")
            .hex("#ffd2cc").build(),
        ColorsEntity.builder().description("vivid Green").name("vividGreen").argb("0xFF00CC7E")
            .hex("#00CC7E").build(),
        ColorsEntity.builder().description("light Mint").name("lightMint").argb("0xFFc5eee8")
            .hex("#c5eee8").build(),
        ColorsEntity.builder().description("dark Input Gray").name("darkInputGray")
            .argb("0xFF232429").hex("#232429").build(),
        ColorsEntity.builder().description("error Red").name("errorRed").argb("0xFFFF2424")
            .hex("#FF2424").build(),
        ColorsEntity.builder().description("success Green").name("successGreen").argb("0xFF37A767")
            .hex("#37A767").build(),
        ColorsEntity.builder().description("toast Success Background")
            .name("toastSuccessBackground").argb("0xFF242C32").hex("#242C32").build(),
        ColorsEntity.builder().description("toast Success Accent").name("toastSuccessAccent")
            .argb("0xFF00DF80").hex("#00DF80").build(),
        ColorsEntity.builder().description("toast Error Background").name("toastErrorBackground")
            .argb("0xFFF04248").hex("#F04248").build(),
        ColorsEntity.builder().description("toast Warning Background")
            .name("toastWarningBackground").argb("0xFFFFD21E").hex("#FFD21E").build(),
        ColorsEntity.builder().description("translucent Gray").name("translucentGray")
            .argb("0x4D4e4e61").hex("#4e4e61").build(),
        ColorsEntity.builder().description("semi Transparent Gray").name("semiTransparentGray")
            .argb("0x593e6133").hex("#4E4E61").build(),
        ColorsEntity.builder().description("border Gray").name("borderGray").argb("0x33cfcffc")
            .hex("#cfcffc").build(),
        ColorsEntity.builder().description("transparent").name("transparent").argb("0x00000000")
            .hex("#00000000").build(),
        ColorsEntity.builder().description("light Overlay").name("lightOverlay").argb("0x4D4e4e61")
            .hex("#4e4e61").build());


    colors.stream().forEach(color -> {
      var colorExist = colorRepository.findByName(color.getName());

      if (colorExist.isEmpty()) {
        this.colorRepository.save(color);
      }
    });
  }

}
