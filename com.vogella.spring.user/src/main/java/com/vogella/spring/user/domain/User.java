package com.vogella.spring.user.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	private long id;
	private String userName = "";
	private String userEmail = "";
	private String password = "";
	private List<String> roles = new ArrayList<>();
	private Instant lastLogin = Instant.now();
	private boolean enabled;
	
	public User(long id) {
		this.id = id;
	}
}