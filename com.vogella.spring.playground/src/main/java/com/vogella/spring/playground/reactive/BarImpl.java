package com.vogella.spring.playground.reactive;

import com.vogella.spring.playground.di.Beer;
import com.vogella.spring.playground.di.BeerImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

public class BarImpl implements Bar {

	@Override
	public Mono<Beer> getRandomBeer() {
		return Mono.create((MonoSink<Beer> sink) -> {
			sink.success(new BeerImpl(""));
		});
	}

	@Override
	public Flux<Beer> getAllBeer() {
		return Flux.create(sink -> {
			sink.next(new BeerImpl(""));

			sink.complete();
		});
	}

	@Override
	public Mono<Void> addBeer(Beer beer) {
		// TODO Auto-generated method stub
		return null;
	}
}
