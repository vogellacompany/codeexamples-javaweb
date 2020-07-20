package com.vogella.spring.quiz.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@EventListener
    public void appReady(ApplicationReadyEvent event) {
		int[] courses = {1, 2}; 
		User user = new User("cxu@vogella.com", courses, "TestUser1");
        this.userRepository.save(user);
        String e = "cxu@vogella.com";
        int[] courses2 = {1, 2}; 
		User user2 = new User("testuser@vogella.com", courses2, "TestUser2");
        user = this.userRepository.save(user2);
        User user3 = new User("testuser@vogella.com", courses2, "TestUser3");
        user = this.userRepository.save(user3);
    }
	
	@GetMapping("/{userId}")
	 public ResponseEntity<User> getUsers(@PathVariable("userId") Optional<User> userOptional) {
		
		/*
		if (!userOptional.isPresent() ) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
		*/
		return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
      }
	
	@PostMapping("/user")
	public User addUser(@Valid @RequestBody User user) {
		return this.userRepository.save(user);
	}
	
	@GetMapping
	public List<User> getAll(){
		return this.userRepository.findAll();
	}
	
	
	@PutMapping("/update/{id}")
	  User replaceEmployee(@RequestBody User newUser, @PathVariable Long id) {

	    return userRepository.findById(id)
	      .map(user -> {
	        user.setEmail(newUser.getEmail());
	        user.setCourseTaken(newUser.getCourseTaken());
	        user.setUserName(newUser.getUserName());
	        return userRepository.save(user);
	      })
	      .orElseGet(() -> {
	        newUser.setUserId(id);
	        return userRepository.save(newUser);
	      });
	  }
}
