package com.vogella.spring.user.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	@Id
	private long id;
	@Builder.Default
	private String name = "";

	@NotEmpty
	@Email
	@Builder.Default
	private String email = "";

	@Size(min = 8, max = 254)
	@Builder.Default
	private String password = "";

	@Builder.Default
	private List<String> roles = new ArrayList<>();

	@Builder.Default
	private Instant lastLogin = Instant.now();

	private boolean enabled;
	
	public User(long id) {
		this.id = id;
	}

}