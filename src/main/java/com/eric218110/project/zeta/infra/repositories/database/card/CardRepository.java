package com.eric218110.project.zeta.infra.repositories.database.card;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eric218110.project.zeta.domain.entities.card.CardEntity;
import com.eric218110.project.zeta.domain.entities.user.UserEntity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, String> {

  List<CardEntity> findByUser(UserEntity userEntity);

  Page<CardEntity> findByUser(UserEntity userEntity, Pageable pageable);

}
