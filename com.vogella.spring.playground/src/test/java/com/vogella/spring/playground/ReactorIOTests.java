package com.vogella.spring.playground;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.vogella.spring.playground.di.Beer;
import com.vogella.spring.playground.di.BeerImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ReactorIOTests {

	private BeerImpl astra;
	private Mono<Beer> astraMono;
	private Mono<Beer> flensburgerMono;

	private Flux<Beer> beerFlux1;
	private Flux<Beer> beerFlux2;

	@Before
	public void before() {
		astra = new BeerImpl("Astra");
		Beer becks = new BeerImpl("Becks");
		Beer carlsberg = new BeerImpl("Carlsberg");
		Beer krombacher = new BeerImpl("Krombacher");

		astraMono = Mono.just(astra);

		beerFlux1 = Flux.just(astra, becks, carlsberg);
		beerFlux2 = Flux.just(carlsberg, krombacher);

		flensburgerMono = Mono.create(sink -> {
			try {
				// Good beer will take a while ;-)
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sink.success(new BeerImpl("Flensburger"));
		});
	}

	@Test
	public void testNextValue() {
		StepVerifier.create(astraMono).expectNext(astra).verifyComplete();
	}

	@Test
	public void testConcatFlux() {
		Flux<Beer> concat = Flux.concat(beerFlux1, beerFlux2);

		StepVerifier.create(concat).recordWith(ArrayList::new).expectNextCount(5).consumeRecordedWith(beers -> {
			assertThat(beers).extracting(Beer::getName).contains("Astra", "Becks", "Carlsberg", "Krombacher");
		});
	}
}
