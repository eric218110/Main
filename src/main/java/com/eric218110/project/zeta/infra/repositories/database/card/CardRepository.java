package com.eric218110.project.zeta.infra.repositories.database.card;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eric218110.project.zeta.data.entities.card.CardEntity;
import com.eric218110.project.zeta.data.entities.user.UserEntity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {

  List<CardEntity> findByUser(UserEntity userEntity);

}
