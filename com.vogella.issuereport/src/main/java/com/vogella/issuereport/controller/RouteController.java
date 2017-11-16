package com.vogella.issuereport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vogella.issuereport.IssueReport;
import com.vogella.issuereport.repositories.IssueRepository;

@Controller
public class RouteController {

	@Autowired
	IssueRepository issueRepository;
	
	@GetMapping("/issuereport")
	public String getReport(Model model) {
		model.addAttribute("issuereport", new IssueReport());
		return "issuereport_form";
	}
	
	@PostMapping(value="/issuereport")
	public String submitReport(@ModelAttribute IssueReport issueReport, Model model) {
		IssueReport result = this.issueRepository.save(issueReport);
		model.addAttribute("submitted", true);
		model.addAttribute("issuereport", result);
		return "issuereport_form";
	}
	
	@GetMapping("/issues")
	public String getIssues(Model model) {
		model.addAttribute("issues", this.issueRepository.findAllButPrivate());
		return "issuereport_list";
	}
	
}
