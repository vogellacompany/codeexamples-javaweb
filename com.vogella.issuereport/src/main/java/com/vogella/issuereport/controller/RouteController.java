package com.vogella.issuereport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vogella.issuereport.IssueReport;
import com.vogella.issuereport.repositories.IssueRepository;

@Controller
public class RouteController {

	@Autowired
	IssueRepository issueRepository;
	
	@GetMapping("/issuereport")
	public ModelAndView getReport() {
		ModelAndView modelAndView = new ModelAndView("issue_report");
		modelAndView.addObject("issuereport", new IssueReport());
		return modelAndView;
	}
	
	@PostMapping(value="/issuereport")
	public ModelAndView submitReport(@ModelAttribute IssueReport issueReport) throws Exception{
		IssueReport result = this.issueRepository.save(issueReport);
		ModelAndView modelAndView = new ModelAndView("issue_report");
		modelAndView.addObject("issuereport", result);
		
		return modelAndView;
	}
	
	@GetMapping("/issues")
	public ModelAndView getIssues() {
		ModelAndView modelAndView = new ModelAndView("issue_list");
		modelAndView.addObject("issues", this.issueRepository.findAll());
		return modelAndView;
	}
	
}
