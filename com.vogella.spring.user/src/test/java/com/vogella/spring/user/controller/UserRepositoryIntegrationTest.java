package com.vogella.spring.user.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vogella.spring.user.data.UserRepository;
import com.vogella.spring.user.domain.User;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryIntegrationTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void save_validUserInput_canBeFoundWithFindAll() throws Exception {
		userRepository.save(User.builder().id(1).name("Lars Vogel").build())
				.mergeWith(userRepository.save(User.builder().id(2).name("Simon Scholz").build()))
				.blockLast();

		Flux<User> users = userRepository.findAll();
		
		StepVerifier.create(users)
			.recordWith(ArrayList::new)
			.expectNextCount(2)
			.consumeRecordedWith(userList -> {
					assertThat(userList).hasSize(2);
					assertThat(userList).withFailMessage("Should contain user with name <%s>", "Simon Scholz")
							.anyMatch(user -> user.getName().equals("Simon Scholz"));
			}).expectComplete()
			.verify();
	}
}
