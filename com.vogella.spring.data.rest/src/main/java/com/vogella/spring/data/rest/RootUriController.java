package com.vogella.spring.data.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootUriController {
	
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
}
