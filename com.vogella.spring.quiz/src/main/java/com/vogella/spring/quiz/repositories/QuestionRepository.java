 package com.vogella.spring.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vogella.spring.quiz.entities.Question;

public interface QuestionRepository extends  JpaRepository<Question, Long>{
	Question findByQuestionId(long questionId);
}
