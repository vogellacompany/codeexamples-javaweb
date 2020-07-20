package com.vogella.spring.quiz.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "course")
public class Course {
	private String courseName;
	private int[] quiz;
	// 0 for not, 1 for accomplished
	@Column(nullable = false)
	private int[] quizAccomplished;
	private double progress;
	private String info;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long courseId;
	
	public Course() {}
	
	public Course(String newCourseName, int[] newQuiz, double newprogress, String newinfo, int[] quizA){
	    this.courseName=newCourseName;
	    this.quiz = newQuiz;
	    this.progress = newprogress;
	    this.info = newinfo;
	    this.quizAccomplished = quizA;
	  }

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int[] getQuiz() {
		return quiz;
	}

	public void setQuiz(int[] quiz) {
		this.quiz = quiz;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public int[] getQuizAccomplished() {
		return quizAccomplished;
	}

	public void setQuizAccomplished(int[] quizAccomplished) {
		this.quizAccomplished = quizAccomplished;
	}
}
