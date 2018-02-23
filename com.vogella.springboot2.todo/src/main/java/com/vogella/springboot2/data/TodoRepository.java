package com.vogella.springboot2.data;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.vogella.springboot2.domain.Todo;

import reactor.core.publisher.Flux;

public interface TodoRepository extends ReactiveCrudRepository<Todo, Long>, ReactiveQueryByExampleExecutor<Todo> {

	Flux<Todo> findBySummaryContainingIgnoreCase(String textInSummary);

	Flux<Todo> findBySummaryContainingAndDescriptionContainingAllIgnoreCaseAndDoneIsTrue(String summary, String description);
}
