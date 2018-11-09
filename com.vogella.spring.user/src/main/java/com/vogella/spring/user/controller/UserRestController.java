package com.vogella.spring.user.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vogella.spring.user.domain.User;
import com.vogella.spring.user.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
class UserRestController {

	private UserService userService;

	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public Flux<User> getUsers(@RequestParam(name = "limit", required = false, defaultValue = "-1") long limit) {
		return userService.getUsers(limit);
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<User>> getUserById(@PathVariable("id") long id) {
		return userService.findUserById(id)
				.map(user -> ResponseEntity.ok(user))
				.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}

	@PostMapping("/search")
	public Mono<User> getUserByExample(@RequestBody User user) {
		return userService.findUserByExample(user);
	}

	@PostMapping
	public Mono<ResponseEntity<Object>> newUser(@RequestBody User user, ServerHttpRequest req) {
		return userService.newUser(user)
				.map(u -> ResponseEntity.created(URI.create(req.getPath() + "/" + u.getId())).build());
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteUser(@PathVariable("id") int id) {
		return userService.deleteUser(id);
	}
}
