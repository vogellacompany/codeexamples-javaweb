package com.vogella.spring.playground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vogella.spring.playground.di.BarKeeperService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	public void setBeerService(BarKeeperService beerService) {
		beerService.logBeerName();
	}
}
