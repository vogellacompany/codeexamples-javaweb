package com.vogella.spring.quiz.controller;

public class UserNotFoundException extends RuntimeException {
	Long userId;
	
	UserNotFoundException(Long userId){
		super("Could not find user " + userId);
	}
}
