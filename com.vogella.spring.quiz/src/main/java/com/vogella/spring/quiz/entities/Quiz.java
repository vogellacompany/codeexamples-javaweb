package com.vogella.spring.quiz.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "quiz")
public class Quiz {
	private String name;
	private int[] questions;
	private double progress;
	private int totalNum;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long quizId;
	private int result;
	
	public Quiz(){}
	
	public Quiz(String newName, int[] newQ, double newprogress, int numT, long id, int reuslt){
	    this.name = newName;
	    this.questions = newQ;
	    this.progress = newprogress;
	    this.totalNum = numT;
	    this.quizId = id;
	    this.result = result;
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
	
	public int getResult() {
		return result;
	}
	
	public void setResult(int result) {
		this.result = result;
	}
	
}
