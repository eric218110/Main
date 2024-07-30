package com.eric218110.project.zeta.infra.repositories.database.category;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eric218110.project.zeta.domain.entities.category.CategoryEntity;
import com.eric218110.project.zeta.domain.entities.user.UserEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

  List<CategoryEntity> findByUser(UserEntity userEntity);

  List<CategoryEntity> findByName(String name);

  Page<CategoryEntity> findByUser(UserEntity userEntity, Pageable pageable);
}
