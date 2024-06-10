package com.eric218110.project.zeta.infra.repositories.database.role;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eric218110.project.zeta.data.entities.role.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

}
