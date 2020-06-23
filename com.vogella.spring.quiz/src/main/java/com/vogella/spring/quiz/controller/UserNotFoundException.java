package com.vogella.spring.quiz.controller;

public class UserNotFoundException extends RuntimeException {
	UserNotFoundException(Long userId){
		super("Could not find user " + userId);
	}
}
