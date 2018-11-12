package com.vogella.spring.playground.reactive;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vogella.spring.playground.di.Beer;
import com.vogella.spring.playground.di.BeerImpl;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;

@Component
public class BarRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

		Bar bar = new BarImpl();
		System.out.print(bar.getRandomBeer());

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
	}

}
