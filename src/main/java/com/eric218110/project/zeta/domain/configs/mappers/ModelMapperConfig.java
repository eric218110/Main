package com.eric218110.project.zeta.domain.configs.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.eric218110.project.zeta.domain.entities.user.UserEntity;
import com.eric218110.project.zeta.domain.http.login.LoginUserResponse;
import com.eric218110.project.zeta.domain.http.user.AddUserRequestBody;
import com.eric218110.project.zeta.domain.http.user.AddUserResponse;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    var modelMapper = new ModelMapper();

    modelMapper.createTypeMap(UserEntity.class, LoginUserResponse.class)
        .addMapping(UserEntity::getUsername, LoginUserResponse::setUserName)
        .addMapping(UserEntity::getName, LoginUserResponse::setName)
        .addMapping(UserEntity::getUserId, LoginUserResponse::setUuid)
        .addMapping(UserEntity::getRoles, LoginUserResponse::setRoles);

    modelMapper.createTypeMap(AddUserRequestBody.class, UserEntity.class)
        .addMapping(AddUserRequestBody::getName, UserEntity::setName)
        .addMapping(AddUserRequestBody::getPassword, UserEntity::setPassword)
        .addMapping(AddUserRequestBody::getUsername, UserEntity::setUsername);

    modelMapper.createTypeMap(UserEntity.class, AddUserResponse.class)
        .addMapping(UserEntity::getRoles, AddUserResponse::setRoles)
        .addMapping(UserEntity::getUserId, AddUserResponse::setUuid)
        .addMapping(UserEntity::getUsername, AddUserResponse::setUsername);

    return modelMapper;
  }
}

