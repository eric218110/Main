package com.eric218110.project.zeta.infra.repositories.database.account_type;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eric218110.project.zeta.domain.entities.account_type.AccountTypeEntity;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountTypeEntity, UUID> {

  Optional<AccountTypeEntity> findByKey(String key);

}
