package com.vogella.spring.quiz.entities;
import com.vogella.spring.quiz.repositories.UserRepository;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Entity
@Table(name = "user")
public class User {
	private String email;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	private int[] courseTaken;
	
	public User() {}
	
	public User(String email, long userId, int[] courses) {
		this.email = email;
		this.userId = userId;
		this.courseTaken = courses;
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
