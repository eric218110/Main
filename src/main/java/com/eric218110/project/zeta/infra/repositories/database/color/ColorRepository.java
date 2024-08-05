package com.eric218110.project.zeta.infra.repositories.database.color;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eric218110.project.zeta.domain.entities.account.AccountEntity;
import com.eric218110.project.zeta.domain.entities.colors.ColorsEntity;

@Repository
public interface ColorRepository extends JpaRepository<ColorsEntity, UUID> {
  Page<ColorsEntity> findByAccount(AccountEntity accountEntity, Pageable pageable);

  Optional<ColorsEntity> findByName(String name);
}
