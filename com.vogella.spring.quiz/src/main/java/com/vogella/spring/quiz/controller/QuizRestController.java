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

import com.vogella.spring.quiz.entities.Quiz;
import com.vogella.spring.quiz.repositories.QuizRepository;

@RestController
@RequestMapping("/api/quiz")   
public class QuizRestController {
	private QuizRepository quizRepo;
	
	public QuizRestController(QuizRepository quizRepo) {
		this.quizRepo = quizRepo;
	}
	
	@EventListener
    public void appReady(ApplicationReadyEvent event) {
		
		String name = "Java-Quiz-1";
		int[] questions = {1, 2};
		double progress = 45;
		int totalNum = 2;
		long quizId = 1;
		
		Quiz quiz = new Quiz(name, questions, progress, totalNum, quizId);
		this.quizRepo.save(quiz);
	}
	
	@GetMapping("/{quizId}")
	public Quiz getQuiz(@PathVariable("quizId") long quizId) {
		return this.quizRepo.findByQuizId(quizId);
	}
	
	@PostMapping("/quiz")
	public Quiz addQuiz(@Valid @RequestBody Quiz quiz) {
		return this.quizRepo.save(quiz);
	}
}