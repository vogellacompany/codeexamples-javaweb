package com.vogella.spring.quiz.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vogella.spring.quiz.entities.Question;
import com.vogella.spring.quiz.entities.Quiz;
import com.vogella.spring.quiz.repositories.QuestionRepository;



@RestController
@RequestMapping("/api/question")   
public class QuestionRestController {
	private QuestionRepository questionRepo;
	
	public QuestionRestController(QuestionRepository questionRepo) {
		this.questionRepo = questionRepo;
	}
	
	@EventListener
    public void appReady(ApplicationReadyEvent event) {
		
		String description = "select one possible option";
		String[] choice = {"option A this is the correct one", "option B this is the wrong one", "option C this is the wrong one", "option D this is the wrong one"};
		String hint = "link to the tutorial, check out this tutorial on our website";
		String correctChoice = "option A this is the correct one";
		long questionId = 1;
		
		Question question = new Question(description, choice, hint, correctChoice, questionId);
		this.questionRepo.save(question);
	}
	
	
	@GetMapping("/{questionId}")
	public ResponseEntity<Question> getQuestion(@PathVariable("questionId") Optional<Question> questionOptional) {
		String description = "select one possible option";
		String[] choice = {"option A this is the correct one", "option B this is the wrong one", "option C this is the wrong one", "option D this is the wrong one"};
		String hint = "link to the tutorial, check out this tutorial on our website";
		String correctChoice = "option A this is the correct one";
		long questionId = 1;
		
		Question question = new Question(description, choice, hint, correctChoice, questionId);
		question = this.questionRepo.save(question);
		
		//TODO this part skipped for id generation and example data
		/*
		//TODO use the id from example data
		if (!quizOptional.isPresent() ) {
   		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   	} */
		
		return new ResponseEntity<>(questionRepo.findByQuestionId(question.getQuestionId()), HttpStatus.OK); 
    }
	
	
	
	@PostMapping("/question")
	public Question addQuestion(@Valid @RequestBody Question question) {
		return this.questionRepo.save(question);
	}
}
