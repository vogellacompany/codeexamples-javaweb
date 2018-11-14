package com.vogella.spring.playground.reactive;

import java.time.Duration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Component
public class SampleReactorRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		createSampleFlux().subscribe(System.out::println);

		ConnectableFlux<String> hotFlux = createSampleHotFlux();

		hotFlux.subscribe(System.out::println);

		hotFlux.connect();

		Thread.sleep(1000);

		hotFlux.subscribe(System.out::println);
	}

	public static Flux<String> createSampleFlux() {
		return Flux.just("Simon", "Fabian", "Lars")
				.map(String::length)
				.publishOn(Schedulers.parallel())
				.distinct()
				.delayElements(Duration.ofSeconds(2))
				.filter(i -> i % 2 == 0)
				.map(String::valueOf)
				.subscribeOn(Schedulers.single())
				.log();
	}

	public static ConnectableFlux<String> createSampleHotFlux() {
		Flux<String> sampleFlux = createSampleFlux();

		ConnectableFlux<String> connectableFlux = sampleFlux.delayElements(Duration.ofMillis(500)).publish();

		return connectableFlux;
	}
}
