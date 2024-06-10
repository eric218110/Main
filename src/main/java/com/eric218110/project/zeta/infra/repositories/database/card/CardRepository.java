package com.eric218110.project.zeta.infra.repositories.database.card;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eric218110.project.zeta.data.entities.card.CardEntity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {

}
