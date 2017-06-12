package com.vogella.springboot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner fill(MyRepository repo) {
		return (args) ->{
			for(int i = 0; i < 3; i++) {
				MyEntityClass entity = new MyEntityClass();
				entity.setValue("Value " + i);
				repo.save(entity);
			}
		};
	}
}
