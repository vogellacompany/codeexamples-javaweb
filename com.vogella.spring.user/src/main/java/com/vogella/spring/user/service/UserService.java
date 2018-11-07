package com.vogella.spring.user.service;

import java.time.Instant;
import java.util.Collections;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.vogella.spring.user.domain.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

	private Flux<User> users;

	public UserService() {
		users = createUserModel();
	}

	private Flux<User> createUserModel() {
		User user = new User(1, "Fabian Pfaff", "fabian.pfaff@vogella.com", "sdguidsdsghuds",
				Collections.singletonList("ADMIN"), Instant.now(), true);
		User user2 = new User(1, "Simon Scholz", "simon.scholz@vogella.com", "sdguidsdsghuds",
				Collections.singletonList("ADMIN"), Instant.now(), false);
		User user3 = new User(1, "Lars Vogel", "lars.vogel@vogella.com", "sdguidsdsghuds",
				Collections.singletonList("USER"), Instant.now(), true);

		return Flux.just(user, user2, user3);
	}

	public Flux<User> getUsers(@RequestParam(name = "limit", required = false, defaultValue = "-1") long limit) {
		if (-1 == limit) {
			return users;
		}
		return users.take(limit);
	}

	public Mono<User> getUserById(@PathVariable("id") long id) {
		return Mono.from(users.filter(user -> id == user.getId()));
	}

	public Mono<User> newUser(@RequestBody User user) {
		Mono<User> userMono = Mono.just(user);
		users = users.mergeWith(userMono);
		return userMono;
	}

	public Mono<Void> deleteUser(@PathVariable("id") int id) {
		users = users.filter(user -> user.getId() != id);
		return users.then();
	}
}
