package com.eric218110.project.zeta.infra.configurations.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.eric218110.project.zeta.data.configuration.web.security.WebSecurityConfigurations;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations implements WebSecurityConfigurations {

  @Override
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return httpSecurity.build();
  }

  // @Bean
  // JwtDecoder jwtDecoder() {
  // return NimbusJwtDecoder.withPublicKey(this.rsaPublicKey).build();
  // }

}
