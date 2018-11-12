package com.vogella.spring.playground.reactive;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vogella.spring.playground.di.BeerImpl;
import com.vogella.spring.playground.reactive.Bar.Listener;

@Component
public class BarRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

		Bar bar = new BarImpl();
		System.out.print(bar.getRandomBeer());

		bar.addBeer(new BeerImpl("Heineken"), new Listener() {

			@Override
			public void success() {
				System.out.print(bar.getRandomBeer());
			}

			@Override
			public void failure(Exception ex) {
				ex.printStackTrace();
			}
		});
	}

}
