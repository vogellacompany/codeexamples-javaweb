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

import com.vogella.spring.quiz.entities.Course;
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
		
		String description = "The default value of a static integer variable of a class in Java is:";
		String hint = "hint not applicable to this question";
		String correctChoice = "0";
		
		
		
		Question question = new Question(description, "0", "1", "garbage value", "null", hint, correctChoice, false, 1, -1, 
				false, "The correct choice is 0. Since this is defined by Java.");
		this.questionRepo.save(question);
		
		
		question = new Question("Multiple inheritance means", 
				"one class inheriting from more super classes", 
				"more classes inheriting from one super class", 
				"more classes inheriting from more super classes", 
				"None of the above", 
				"First think about the definistion for inheritance", 
				"one class inheriting from more super classes", false, 1, -1, false, 
				"Inheritance is inheriting from one super class, therefore multiple "
				+ "inheritance is inheriting from more superclass");
		this.questionRepo.save(question);

		question = new Question("Which statement is not true in java language?", 
				"A public member of a class can be accessed in all the packages.", 
				"A private member of a class cannot be accessed by the methods of the same class.", 
				"A private member of a class cannot be accessed from its derived class.", 
				"A protected member of a class can be accessed from its derived class.", 
				"the class dependence concept is similar to that of C language", 
				"A private member of a class cannot be accessed by the methods of the same class.", 
				false, 1, -1, false, 
				"Private member of the same class can still be accessed by the methods in the same class");
		this.questionRepo.save(question);
		
		question = new Question("Multiple inheritance means", 
				"one class inheriting from more super classes", 
				"more classes inheriting from one super class", 
				"more classes inheriting from more super classes", 
				"None of the above", 
				"First think about the definistion for inheritance", 
				"one class inheriting from more super classes", false, 1, -1, false, 
				"Inheritance is inheriting from one super class, therefore multiple "
				+ "inheritance is inheriting from more superclass");
		this.questionRepo.save(question);

		question = new Question("Which statement is not true in java language?", 
				"A public member of a class can be accessed in all the packages.", 
				"A private member of a class cannot be accessed by the methods of the same class.", 
				"A private member of a class cannot be accessed from its derived class.", 
				"A protected member of a class can be accessed from its derived class.",
				"the class dependence concept is similar to that of C language", 
				"A private member of a class cannot be accessed by the methods of the same class.", 
				false, 1, -1, false, 
				"Private member of the same class can still be accessed by the methods in the same class");
		this.questionRepo.save(question);
	}
	
	
	@GetMapping("/{questionId}")
	public ResponseEntity<Question> getQuestion(@PathVariable("questionId") Optional<Question> questionOptional) {
	/*	String description = "select one possible option";
		String[] choice = {"option A this is the correct one", "option B this is the wrong one", "option C this is the wrong one", "option D this is the wrong one"};
		String hint = "link to the tutorial, check out this tutorial on our website";
		String correctChoice = "option A this is the correct one";
		long questionId = 1;
		
		Question question = new Question(description, choice, hint, correctChoice, questionId);
		question = this.questionRepo.save(question);
	*/
		//TODO this part skipped for id generation and example data
		/*
		//TODO use the id from example data
		if (!quizOptional.isPresent() ) {
   		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   	} */
		
		return new ResponseEntity<>(questionOptional.get(), HttpStatus.OK);
		//return new ResponseEntity<>(questionRepo.findByQuestionId(question.getQuestionId()), HttpStatus.OK); 
    }
	
	
	
	@PostMapping("/question")
	public Question addQuestion(@Valid @RequestBody Question question) {
		return this.questionRepo.save(question);
	}
	
	@GetMapping
	public List<Question> getAll(){
		return this.questionRepo.findAll();
	}
	
	@PutMapping("/update/{id}")
	  Question replaceQuestion(@RequestBody Question newQuestion, @PathVariable Long id) {

	    return questionRepo.findById(id)
	      .map(question -> {
	        question.setDescription(newQuestion.getDescription());
	        question.setChoiceA(newQuestion.getChoiceA());
	        question.setChoiceB(newQuestion.getChoiceB());
	        question.setChoiceC(newQuestion.getChoiceC());
	        question.setChoiceD(newQuestion.getChoiceD());
	        question.setHint(newQuestion.getHint());
	        question.setCorrectChoice(newQuestion.getCorrectChoice());
	        question.setAttempted(newQuestion.isAttempted());
	        question.setParentQuiz(newQuestion.getParentQuiz());
	        question.setChoiceNum(newQuestion.getChoiceNum());
	        question.setCorrect(newQuestion.isCorrect());
	        question.setExplanation(newQuestion.getExplanation());
	        
	        return questionRepo.save(question);
	      })
	      .orElseGet(() -> {
	        newQuestion.setQuestionId(id);
	        return questionRepo.save(newQuestion);
	      });
	  }
}
