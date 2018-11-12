package com.vogella.spring.playground.reactive;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vogella.spring.playground.di.BeerImpl;

@Component
public class BarRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

		Bar bar = new BarImpl();
		System.out.print(bar.getRandomBeer());

		bar.addBeer(new BeerImpl("Heineken"));
		System.out.print(bar.getRandomBeer());
	}

}
