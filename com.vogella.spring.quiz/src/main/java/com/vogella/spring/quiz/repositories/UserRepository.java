package com.vogella.spring.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vogella.spring.quiz.entities.User;

import java.util.List;

public interface UserRepository extends  JpaRepository<User, Long>{
	List<User> findAllByEmail(String email);
	
	User findByUserId(long userId);
	
	List<User> findAll();
}
