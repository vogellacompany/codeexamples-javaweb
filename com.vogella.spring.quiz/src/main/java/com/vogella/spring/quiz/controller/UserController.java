package com.vogella.spring.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vogella.spring.quiz.entities.User;
import com.vogella.spring.quiz.repositories.UserRepository;

@Controller                     // 
public class UserController {
	
	UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/adduser")
    public String getUser(Model model) {
        model.addAttribute("user", new User()); // #1
        return "user/adduser_form";
    }
	
    @PostMapping(value="/adduser") // 
    public String AddUser(User user, Model model) { // 
    	User result = this.userRepository.save(user); 
        return "user/adduser_form";
    }

}
