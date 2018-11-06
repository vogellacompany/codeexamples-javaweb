package com.vogella.spring.playground.di;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BeerImpl implements Beer {

	private String name;
}
