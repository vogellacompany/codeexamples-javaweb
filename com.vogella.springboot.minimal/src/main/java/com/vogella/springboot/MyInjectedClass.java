package com.vogella.springboot;

import org.springframework.stereotype.Component;

@Component
public class MyInjectedClass {
	
	public String getValue() {
		return "This class was injected";
	}
}
