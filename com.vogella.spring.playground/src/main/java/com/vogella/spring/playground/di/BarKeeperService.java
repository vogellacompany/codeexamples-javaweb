package com.vogella.spring.playground.di;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BarKeeperService {

	Logger LOG = LoggerFactory.getLogger(BarKeeperService.class);

	private List<Beer> beer;

	public BarKeeperService(List<Beer> beer) {
		this.beer = beer;
	}

	public void logBeerName() {
		beer.stream().map(Beer::getName).forEach(LOG::info);
	}
}
