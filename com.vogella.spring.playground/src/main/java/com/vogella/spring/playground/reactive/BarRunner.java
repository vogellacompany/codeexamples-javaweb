package com.vogella.spring.playground.reactive;

import java.time.Duration;

import org.springframework.boot.CommandLineRunner;

import com.vogella.spring.playground.di.Beer;
import com.vogella.spring.playground.di.BeerImpl;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class BarRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

		Bar bar = new BarImpl();
		System.out.print(bar.getRandomBeer());

		Mono<Beer> randomBeer = bar.getRandomBeer();
		Mono<Beer> cache = randomBeer.cache();

		cache.subscribe();
		cache.subscribe();
		cache.subscribe();

		bar.addBeer(new BeerImpl("Heineken"));

		Flux<Beer> allBeer = bar.getAllBeer();

		Disposable disposable = allBeer.subscribe(beer -> {
			System.out.println("Enjoying my delicious " + beer.getName());
		}, error -> {
			System.err.println("Someone broke the beer class");
			error.printStackTrace();
		}, () -> System.out.println("Beer's empty :-("));

		// I have to quit drinking beer
		disposable.dispose();

		Flux.just(1, 2, 2, 3, 4, 5, 6, 7, 8, 9)
			.publishOn(Schedulers.parallel())
			.distinct()
			.delayElements(Duration.ofSeconds(2))
			.filter(i -> i % 2 == 0)
			.map(String::valueOf)
			.subscribeOn(Schedulers.single())
			.subscribe(System.out::println);

		System.out.println("Hello World!");
	}

}
