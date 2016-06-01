package com.vogella.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.vogella" })
public class Config {

	@Bean
	public Long getId() {
		return Long.valueOf(0);
	}

	@Bean
	@Qualifier("summary")
	public String getSummary() {
		return "Spring DI";
	}
	
	@Bean
	@Qualifier("description")
	public String getDescription() {
		return "Dependency in Spring is a powerful feauture for inversion of control.";
	}
	
	@Bean
	public Boolean isDone() {
		return Boolean.FALSE;
	}

	@Bean
	public Date getDueDate() {
		return new Date();
	}
}
