package com.eric218110.project.zeta.infra.configurations.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.eric218110.project.zeta.data.configuration.web.security.WebSecurityConfigurations;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations implements WebSecurityConfigurations {

  @Value("${security.jwt.key.public}")
  private RSAPublicKey rsaPublicKey;

  @Value("${security.jwt.key.private}")
  private RSAPrivateKey rsaPrivateKey;

  @Override
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
            authorize -> authorize.requestMatchers(HttpMethod.POST).hasRole("ADMIN").anyRequest().authenticated())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return httpSecurity.build();
  }

}
