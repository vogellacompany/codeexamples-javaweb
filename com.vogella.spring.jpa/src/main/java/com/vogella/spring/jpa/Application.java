package com.vogella.spring.jpa;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.vogella.spring.jpa.repository.TodoRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner jpaSample(TodoRepository todoRepo) {
		return (args) -> {
			
			// save 2 todos in the H2 database
			todoRepo.save(new Todo("Test"));

			Todo todo = new Todo("Detailed test");
			Date date = new Date();
			todo.setDueDate(date);
			todo.setDescription("Detailed description");
			todoRepo.save(todo);

			// query for all todos in the H2 database and print them
			todoRepo.findAll().forEach(System.out::println);
			
			todoRepo.getBySummary("Detailed test").forEach(System.out::println);
			
			todoRepo.findBySummaryAndDueDate("Detailed test", date).ifPresent(System.out::println);

			todoRepo.readBySummaryOrDescription("Detailed test", "bla bla find me anyways").forEach(System.out::println);

			System.out.println(todoRepo.count());

			System.out.println(todoRepo.countBySummary("Detailed test"));
			
			todoRepo.getTodosOf2016().forEach(System.out::println);
			
			CompletableFuture<List<Todo>> asyncJava8BySummary = todoRepo.findAsyncJava8BySummary("Test");
			asyncJava8BySummary.thenAccept(t -> t.forEach(System.out::println));
		};
	}
}
