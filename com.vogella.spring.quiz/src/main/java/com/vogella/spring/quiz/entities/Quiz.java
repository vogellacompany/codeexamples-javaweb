package com.vogella.spring.quiz.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private int parentCourse;
	private boolean finished;
	private int[] answerList;
	private int[] correctList;
	
	
	public Quiz(){}
	
	public Quiz(String newName, int[] newQ, double newprogress, int numT, int newResult,
			int parent, boolean finish, int[] answerL, int[] correctL){
	    this.name = newName;
	    this.questions = newQ;
	    this.progress = newprogress;
	    this.totalNum = numT;
	    this.result = newResult;
	    this.parentCourse = parent;
	    this.finished = finish;
	    this.answerList = answerL;
	    this.correctList = correctL;
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

	public int getParentCourse() {
		return parentCourse;
	}

	public void setParentCourse(int parentCourse) {
		this.parentCourse = parentCourse;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public int[] getAnswerList() {
		return answerList;
	}

	public void setAnswerList(int[] answerList) {
		this.answerList = answerList;
	}

	public int[] getCorrectList() {
		return correctList;
	}

	public void setCorrectList(int[] correctList) {
		this.correctList = correctList;
	}
	
}
