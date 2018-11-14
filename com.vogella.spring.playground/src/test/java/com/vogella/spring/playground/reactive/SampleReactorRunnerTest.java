package com.vogella.spring.playground.reactive;

import org.junit.Before;
import org.junit.Test;

import com.vogella.spring.playground.reactive.SampleReactorRunner;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class SampleReactorRunnerTest {

	private Flux<String> sampleFlux;

	@Before
	public void setUp() throws Exception {
		sampleFlux = SampleReactorRunner.createSampleFlux();
	}

	@Test
	public void test() {
		StepVerifier.create(sampleFlux)
					.expectNext("6", "4")
					.expectComplete();
	}
}
