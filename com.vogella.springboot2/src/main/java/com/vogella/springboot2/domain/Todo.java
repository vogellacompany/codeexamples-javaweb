package com.vogella.springboot2.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Todo {

	private long id;
	private String summary = "";
	private String description = "";
	private boolean done = false;
	private Date dueDate = new Date();
	
	public Todo(long id) {
		this.id = id;
	}
}
