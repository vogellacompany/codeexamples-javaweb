package com.vogella.springboot2.service;

import org.springframework.stereotype.Service;

import com.vogella.springboot2.domain.Todo;

import reactor.core.publisher.Flux;

@Service
public class TodoService {

	private Flux<Todo> todos;

	public TodoService() {
		todos = createTodoModel();
	}

	private Flux<Todo> createTodoModel() {
		Todo todo = new Todo(1);
		todo.setSummary("Learn Spring Boot 2.0");
		todo.setDescription("Easily create modern reactive webapps with Spring Boot 2.0");

		Todo todo2 = new Todo(2);
		todo2.setSummary("Learn Reactor Framework");
		todo2.setDescription("Use the power of the reactive io api with the Reactor Framework");

		Todo todo3 = new Todo(3);
		todo3.setSummary("Learn @RestController");
		todo3.setDescription("Learn how to create @RestController and use rest endpoints");

		return Flux.just(todo, todo2, todo3);
	}

	public Flux<Todo> getTodos(long limit) {
		if (-1 == limit) {
			return todos;
		}
		return todos.take(limit);
	}

	public Flux<Todo> getTodoById(long id) {
		return todos.filter(t -> id == t.getId());
	}

	public Flux<Todo> newTodo(Todo todo) {
		todos = todos.mergeWith(Flux.just(todo));
		return todos;
	}

	public Flux<Todo> deleteTodo(int id) {
		todos = todos.filter(todo -> todo.getId() != id);
		return todos;
	}
}
