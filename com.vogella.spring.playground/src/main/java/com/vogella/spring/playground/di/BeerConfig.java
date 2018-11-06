package com.vogella.spring.playground.di;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/beers.properties")
public class BeerConfig {
	
	@Bean
	@Qualifier("Becks")
	public Beer getBecks() {
		return new Beer() {
			@Override
			public String getName() {
				return "Becks";
			}
		};
	}

	@Bean
	public Beer getBeerNameFromProperty(@Value("${beer.name}") String beerName) {
		return new Beer() {
			@Override
			public String getName() {
				return beerName;
			}
		};
	}

	@Bean
	public List<Beer> getBeerNamesFromProperty(@Value("${beer.names}") List<String> beerNames) {
		return beerNames.stream().map(BeerImpl::new).collect(Collectors.toList());
	}
}