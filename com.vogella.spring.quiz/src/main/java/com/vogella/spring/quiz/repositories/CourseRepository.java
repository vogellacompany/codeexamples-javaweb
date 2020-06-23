package com.vogella.spring.quiz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vogella.spring.quiz.entities.Course;

public interface CourseRepository extends  JpaRepository<Course, Long>{
	Course findByCourseId(long courseId);
}