package com.vogella.springboot2.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vogella.springboot2.domain.Todo;
import com.vogella.springboot2.service.TodoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/todo")
class TodoRestController {

	private TodoService todoService;

	public TodoRestController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping
	public Flux<Todo> getTodos(@RequestParam(name = "limit", required = false, defaultValue = "-1") long limit) {
		return todoService.getTodos(limit);
	}

	@GetMapping("/search")
	public Mono<Todo> getTodoById(long id) {
		return todoService.getTodoById(id);
	}

	@GetMapping("/search")
	public Flux<Todo> getBySummary(String summary) {
		return todoService.getBySummary(summary);
	}

	@PostMapping
	public Mono<Todo> newTodo(@RequestBody Todo todo) {
		return todoService.newTodo(todo);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteTodo(@PathVariable("id") int id) {
		return todoService.deleteTodo(id);
	}
}
