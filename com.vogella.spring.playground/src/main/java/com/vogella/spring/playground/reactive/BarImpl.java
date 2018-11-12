package com.vogella.spring.playground.reactive;

import java.util.concurrent.TimeUnit;

import com.vogella.spring.playground.di.Beer;
import com.vogella.spring.playground.di.BeerImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

public class BarImpl implements Bar {

	@Override
	public Mono<Beer> getRandomBeer() {
		return Mono.create((MonoSink<Beer> sink) -> {
			try {
				// imagine a long running operation
				TimeUnit.SECONDS.wait(3);
			} catch (InterruptedException e) {
				sink.error(e);
			}
			sink.success(new BeerImpl(""));
		});
	}

	@Override
	public Flux<Beer> getAllBeer() {
		return Flux.create(sink -> {
			try {
				// imagine a long running operation
				TimeUnit.SECONDS.wait(3);
			} catch (InterruptedException e) {
				sink.error(e);
			}
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
