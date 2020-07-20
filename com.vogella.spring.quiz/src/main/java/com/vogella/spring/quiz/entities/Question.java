package com.vogella.spring.quiz.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name = "question")
public class Question {
	private String description;
	private String choiceA;
	private String choiceB;
	private String choiceC;
	private String choiceD;
	private String hint;
	private String correctChoice;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long questionId;
	private boolean attempted;
	private int parentQuiz;
	private int choiceNum;
	private boolean correct;
	private String explanation;
	
	
	public Question(){}
	
	public Question(String newDescrip, String a, String b, String c, String d, String newHint, String correctC,
			boolean newAttempted, int parentQ, int choiceN, boolean corr, String explan){
	    this.description = newDescrip;
	    this.choiceA = a;
	    this.choiceB = b;
	    this.choiceC = c;
	    this.choiceD = d;
	    this.hint = newHint;
	    this.correctChoice = correctC;
	    
	    this.attempted = newAttempted;
	    this.parentQuiz = parentQ;
	    this.choiceNum = choiceN;
	    this.correct = corr;
	    this.explanation = explan;
	  }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getCorrectChoice() {
		return correctChoice;
	}

	public void setCorrectChoice(String correctChoice) {
		this.correctChoice = correctChoice;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public boolean isAttempted() {
		return attempted;
	}

	public void setAttempted(boolean attempted) {
		this.attempted = attempted;
	}

	public int getParentQuiz() {
		return parentQuiz;
	}

	public void setParentQuiz(int parentQuiz) {
		this.parentQuiz = parentQuiz;
	}

	public int getChoiceNum() {
		return choiceNum;
	}

	public void setChoiceNum(int choiceNum) {
		this.choiceNum = choiceNum;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getChoiceA() {
		return choiceA;
	}

	public void setChoiceA(String choiceA) {
		this.choiceA = choiceA;
	}

	public String getChoiceB() {
		return choiceB;
	}

	public void setChoiceB(String choiceB) {
		this.choiceB = choiceB;
	}

	public String getChoiceC() {
		return choiceC;
	}

	public void setChoiceC(String choiceC) {
		this.choiceC = choiceC;
	}

	public String getChoiceD() {
		return choiceD;
	}

	public void setChoiceD(String choiceD) {
		this.choiceD = choiceD;
	}
}
