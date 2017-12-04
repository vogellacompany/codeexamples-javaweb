package com.vogella.issuereport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vogella.issuereport.IssueReport;
import com.vogella.issuereport.repositories.IssueRepository;

@RestController
public class ApiController {

	@Autowired
	IssueRepository issueRepository;
	
	@GetMapping(value="/api/issues")
	public List<IssueReport> getIssues() {
		return this.issueRepository.findAllButPrivate();
	}
	
	@GetMapping(value="/api/issues/{id}")
	public IssueReport getIssue(@PathVariable(value="id") long id) {
		return this.issueRepository.findOne(id);
	}
	
}
