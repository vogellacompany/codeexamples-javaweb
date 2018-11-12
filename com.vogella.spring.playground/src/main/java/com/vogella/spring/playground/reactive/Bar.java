package com.vogella.spring.playground.reactive;

import com.vogella.spring.playground.di.Beer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Bar {
	public Mono<Beer> getRandomBeer();
	public Flux<Beer> getAllBeer();
	public Mono<Void> addBeer(Beer beer);
}
