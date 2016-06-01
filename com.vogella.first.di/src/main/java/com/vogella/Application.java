package com.vogella;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vogella.config.Config;

public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		
		ITodo todo = context.getBean(ITodo.class);
		
		System.out.println(todo);
		System.out.println(todo.getDueDate());
		
		context.close();
	}
}
