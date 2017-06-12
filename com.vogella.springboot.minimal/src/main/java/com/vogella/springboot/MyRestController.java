package com.vogella.springboot;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
	
	@Autowired
	MyInjectedClass myInjectedClass;

	@Autowired
	MyRepository repository;
	
	@RequestMapping("inject")
	public String getValue() {
		return myInjectedClass.getValue(); 
	}
	
	@RequestMapping("entities")
	public List<MyEntityClass> getEntities() {
		List<MyEntityClass> list = new ArrayList<MyEntityClass>();
		repository.findAll().forEach(list::add);
		return list;
	}
}
