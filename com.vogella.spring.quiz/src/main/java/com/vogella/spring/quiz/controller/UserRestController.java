package com.vogella.spring.quiz.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vogella.spring.quiz.repositories.UserRepository;

import com.vogella.spring.quiz.entities.User;

@RestController
@RequestMapping("/api/user")   
public class UserRestController {
	private UserRepository userRepository;
	
	public UserRestController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUsers(@PathVariable("id") Optional<User> userOptional) {
		if(!userOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
	}
	
	@PostMapping("/user")
	public User addUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}
}
