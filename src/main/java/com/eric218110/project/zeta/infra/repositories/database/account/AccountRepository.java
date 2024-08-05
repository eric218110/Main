package com.eric218110.project.zeta.infra.repositories.database.account;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eric218110.project.zeta.domain.entities.account.AccountEntity;
import com.eric218110.project.zeta.domain.entities.user.UserEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
  Page<AccountEntity> findByUser(UserEntity userEntity, Pageable pageable);
}
