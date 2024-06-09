package com.eric218110.project.zeta.data.configuration.web.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public interface WebSecurityConfigurations {
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception;
}
