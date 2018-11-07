package com.vogella.spring.user.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.vogella.spring.user.domain.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

}
