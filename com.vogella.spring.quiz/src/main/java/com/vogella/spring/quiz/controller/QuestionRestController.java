package com.vogella.spring.quiz.controller;

import javax.validation.Valid;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vogella.spring.quiz.entities.Question;
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
		String[] choice = {"option A hkjdfhakj", "option B dkljfl df", "option C dfkljfjhksd", "option D fdkjhasdlhf"};
		String hint = "link to the tutorial";
		String correctChoice = "option A hkjdfhakj";
		long questionId = 1;
		
		Question question = new Question(description, choice, hint, correctChoice, questionId);
		this.questionRepo.save(question);
	}
	
	@GetMapping("/{questionId}")
	public Question getQuestion(@PathVariable("questionId") long questionId) {
		return this.questionRepo.findByQuestionId(questionId);
	}
	
	@PostMapping("/question")
	public Question addQuestion(@Valid @RequestBody Question question) {
		return this.questionRepo.save(question);
	}
}
