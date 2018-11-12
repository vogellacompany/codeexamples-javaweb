package com.vogella.spring.playground.reactive;

import java.util.List;

import com.vogella.spring.playground.di.Beer;

public interface Bar {
	public Beer getRandomBeer();
	public List<Beer> getAllBeer();
	public void addBeer(Beer beer);
}
