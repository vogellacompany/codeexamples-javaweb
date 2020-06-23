package com.vogella.spring.quiz.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "quiz")
public class Quiz {
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String name;
	private int[] questions;
	private double progress;
	private int totalNum;
	private long quizId;
	
	public Quiz(){}
	
	public Quiz(String newName, int[] newQ, double newprogress, int numT, long id){
	    this.name = newName;
	    this.questions = newQ;
	    this.progress = newprogress;
	    this.totalNum = numT;
	    this.quizId = id;
	  }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getQuestions() {
		return questions;
	}

	public void setQuestions(int[] questions) {
		this.questions = questions;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public long getQuizId() {
		return quizId;
	}

	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}
	
}
