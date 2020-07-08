package com.vogella.spring.quiz.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "course")
public class Course {
	private String courseName;
	private int[] quiz;
	private double progress;
	private String info;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long courseId;
	
	public Course() {}
	
	public Course(String newCourseName, int[] newQuiz, double newprogress, String newinfo, long id){
	    this.courseName=newCourseName;
	    this.quiz = newQuiz;
	    this.progress = newprogress;
	    this.info = newinfo;
	    this.courseId  = id;
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
}
