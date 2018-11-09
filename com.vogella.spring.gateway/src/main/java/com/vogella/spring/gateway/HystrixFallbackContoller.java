package com.vogella.spring.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
class HystrixFallbackContoller {

	@GetMapping("/fallback")
	public Mono<String> userFallback() {
		return Mono.just("Please tell the admin to start a user service");
	}
}
