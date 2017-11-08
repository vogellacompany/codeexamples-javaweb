package com.vogella.issuereport.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vogella.issuereport.IssueReport;

public interface IssueRepository extends CrudRepository<IssueReport, Long>{

	List<IssueReport> findAllByEmail(String email);
	
}
