package com.vogella.spring.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vogella.spring.user.config.JWTUtil;
import com.vogella.spring.user.config.ServiceReactiveUserDetailsService;
import com.vogella.spring.user.domain.User;

import reactor.core.publisher.Mono;

@RestController
public class AuthRestController {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ServiceReactiveUserDetailsService userRepository;

	@PostMapping("/auth")
	public Mono<ResponseEntity<AuthResponse>> auth(@RequestBody AuthRequest ar) {
		return userRepository
			.findByUsername(ar
				.getEmail())
			.map((userDetails) -> {
				if (passwordEncoder
					.matches(ar
						.getPassword(),
							userDetails
								.getPassword())) {
					return ResponseEntity
						.ok(new AuthResponse(jwtUtil
							.generateToken((User) userDetails)));
				} else {
					return ResponseEntity
						.status(HttpStatus.UNAUTHORIZED)
						.build();
				}
			});
	}
}
