package com.vogella.spring.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
class HystrixFallbackContoller {

	@GetMapping("/fallback")
	public Mono<ResponseEntity<String>> userFallback() {
		return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
	}
}
