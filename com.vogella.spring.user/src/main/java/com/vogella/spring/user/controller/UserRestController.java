package com.vogella.spring.user.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public Mono<User> getUserById(@PathVariable("id") long id) {
		return userService.findUserById(id);
	}

	@PostMapping
	public Mono<User> newUser(@RequestBody User user) {
		return userService.newUser(user);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteUser(@PathVariable("id") int id) {
		return userService.deleteUser(id);
	}
}
