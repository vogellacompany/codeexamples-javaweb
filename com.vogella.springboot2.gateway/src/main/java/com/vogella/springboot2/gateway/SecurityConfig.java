package com.vogella.springboot2.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http)
			throws Exception {
		// @formatter:off
		http
			.authorizeExchange()
				.pathMatchers("/todo/**").hasAuthority("SCOPE_read")
				.anyExchange().authenticated()
				.and()
			.oauth2ResourceServer()
				.jwt();
		// @formatter:on
		return http.build();
	}

}