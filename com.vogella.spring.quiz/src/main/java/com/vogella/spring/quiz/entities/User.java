package com.vogella.spring.quiz.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;


@Entity
@Table(name = "user")
public class User {
	@Column(nullable = false)
	private String email;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	private int[] courseTaken;	
	@Column(nullable = false)
	private String userName;
	
	public User() {}
	
	public User(String email, int[] courses, String newUserName) {
		this.email = email;
		this.courseTaken = courses;
		this.userName = newUserName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int[] getCourseTaken() {
		return courseTaken;
	}

	public void setCourseTaken(int[] courseTaken) {
		this.courseTaken = courseTaken;
	}
	
	
}
