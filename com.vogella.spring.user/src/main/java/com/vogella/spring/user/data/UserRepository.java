package com.vogella.spring.user.data;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.vogella.spring.user.domain.User;

import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveCrudRepository<User, Long>, ReactiveQueryByExampleExecutor<User> {

	Flux<User> findByEmailContainingIgnoreCase(String email);

	Flux<User> findByEmailContainingAndRolesContainingAllIgnoreCaseAndEnabledIsTrue(String email, String role);
}
