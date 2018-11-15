package com.vogella.spring.user.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.stereotype.Service;

import com.vogella.spring.user.data.UserRepository;
import com.vogella.spring.user.domain.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository UserRepository) {
		this.userRepository = UserRepository;
	}

	public Flux<User> getUsers(long limit) {
		if (-1 == limit) {
			return userRepository.findAll();
		}
		return userRepository.findAll().take(limit);
	}

	public Mono<User> findUserById(long id) {
		return userRepository.findById(id);
	}

	public Mono<User> findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Mono<User> findUserByExample(User user) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withMatcher("email", GenericPropertyMatcher::contains)
				.withMatcher("role", GenericPropertyMatcher::contains)
				.withMatcher("enabled", GenericPropertyMatcher::exact);
		Example<User> example = Example.of(user, matcher);
		return userRepository.findOne(example);
	}

	public Mono<User> newUser(User User) {
		return userRepository.save(User);
	}

	public Mono<Void> deleteUser(long id) {
		return userRepository.deleteById(id);
	}

}
