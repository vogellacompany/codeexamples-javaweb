package com.vogella.spring.user.controller;

import java.time.Instant;
import java.util.Collections;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.vogella.spring.user.domain.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
class UserRestController {

	Flux<User> users;

	public UserRestController() {
		users = createUserModel();
	}

	private Flux<User> createUserModel() {
		User user = new User(1, "Fabian Pfaff", "fabian.pfaff@vogella.com", "sdguidsdsghuds",
				Collections.singletonList("ADMIN"), Instant.now(), true);
		User user2 = new User(2, "Simon Scholz", "simon.scholz@vogella.com", "sdguidsdsghuds",
				Collections.singletonList("ADMIN"), Instant.now(), false);
		User user3 = new User(3, "Lars Vogel", "lars.vogel@vogella.com", "sdguidsdsghuds",
				Collections.singletonList("USER"), Instant.now(), true);

		return Flux.just(user, user2, user3);
	}

	@GetMapping
	public Flux<User> getUsers(@RequestParam(name = "limit", required = false, defaultValue = "-1") long limit) {
		if (-1 == limit) {
			return users;
		}
		return users.take(limit);
	}

	@GetMapping("/{id}")
	public Mono<ServerResponse> getUserById(@PathVariable("id") long id) {
		Mono<User> foundUser = Mono.from(users.filter(user -> id == user.getId()));
		return foundUser
				.flatMap(user -> ServerResponse.ok().body(Mono.just(user), User.class))
				.switchIfEmpty(ServerResponse.notFound().build());
//				.switchIfEmpty(Mono.error(new NotFoundException()));
	}

	@PostMapping
	public Mono<User> newUser(@RequestBody User user) {
		Mono<User> userMono = Mono.just(user);
		users = users.mergeWith(userMono);
		return userMono;
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteTodo(@PathVariable("id") int id) {
		users = users.filter(user -> user.getId() != id);
		return users.then();
	}
}
