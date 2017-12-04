package com.vogella.issuereport.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.vogella.issuereport.repositories.IssueRepository;

@Controller
public class AdminController {

	@Autowired
	IssueRepository issueRepository;
	
	@GetMapping("/admin")
	public String getAdmin(Model model) {
		model.addAttribute("issues", this.issueRepository.findAll());
		return "admin/admin";
	}
	
	@PostMapping("/admin/update")
	public String updateIssue(Model model) {
		return "admin/admin";
	}
	
}
