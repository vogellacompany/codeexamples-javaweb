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

import com.vogella.spring.quiz.entities.Question;
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
		
		String name = "Quiz-1";
		int[] questions = {1, 2, 3, 4, 5};
		double progress = 0;
		int totalNum = 5;
		int result = -1;
		
		// -1 to indicate unattempted	
		int[] answerL = {-1, -1, -1, -1, -1};
		int[] correctL = {-1, -1, -1, -1, -1};
		
		Quiz quiz = new Quiz(name, questions, progress, totalNum, result, 1, false, answerL, correctL);
		this.quizRepo.save(quiz);
		
		quiz = new Quiz("test quiz 2", questions, progress, totalNum, result, 1, false, answerL, correctL);
		this.quizRepo.save(quiz);
		
		quiz = new Quiz("test quiz 3", questions, progress, totalNum, result, 1, false, answerL, correctL);
		this.quizRepo.save(quiz);
	}
	
	@GetMapping("/{quizId}")
	public ResponseEntity<Quiz> getQuiz(@PathVariable("quizId") Optional<Quiz> quizOptional) {
	/*	String name = "Java-Quiz-1";
		int[] questions = {1, 2, 3, 4, 5, 6};
		double progress = 45;
		int totalNum = 2;
		long quizId = 1;
		int result = -1;
		
		Quiz quiz = new Quiz(name, questions, progress, totalNum, quizId, result);
		quiz = this.quizRepo.save(quiz); */
		
		//TODO this part skipped for id generation and example data
		/*
		//TODO use the id from example data
		if (!quizOptional.isPresent() ) {
   		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   	} */
		
		//return new ResponseEntity<>(quizRepo.findByQuizId(3), HttpStatus.OK); 
		return new ResponseEntity<>(quizOptional.get(), HttpStatus.OK);
  
    }
	
	@PostMapping("/quiz")
	public Quiz addQuiz(@Valid @RequestBody Quiz quiz) {
		return this.quizRepo.save(quiz);
	}
	
	@GetMapping
	public List<Quiz> getAll(){
		return this.quizRepo.findAll();
	}
	
	@PutMapping("/update/{id}")
	  Quiz replaceQuiz(@RequestBody Quiz newQuiz, @PathVariable Long id) {

	    return quizRepo.findById(id)
	      .map(quiz -> {
	        quiz.setName(newQuiz.getName());
	        quiz.setQuestions(newQuiz.getQuestions());
	        quiz.setProgress(newQuiz.getProgress());
	        quiz.setTotalNum(newQuiz.getTotalNum());
	        quiz.setResult(newQuiz.getResult());
	        quiz.setParentCourse(newQuiz.getParentCourse());
	        quiz.setFinished(newQuiz.isFinished());
	        quiz.setAnswerList(newQuiz.getAnswerList());
	        quiz.setCorrectList(newQuiz.getCorrectList());  
	        return quizRepo.save(quiz);
	      })
	      .orElseGet(() -> {
	        newQuiz.setQuizId(id);
	        return quizRepo.save(newQuiz);
	      });
	  }
}