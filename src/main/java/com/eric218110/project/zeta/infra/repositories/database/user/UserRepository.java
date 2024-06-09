package com.eric218110.project.zeta.infra.repositories.database.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eric218110.project.zeta.data.entities.user.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
