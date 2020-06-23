package com.vogella.spring.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vogella.spring.quiz.entities.Quiz;


public interface QuizRepository extends  JpaRepository<Quiz, Long>{
	Quiz findByQuizId(long quizId);
}
