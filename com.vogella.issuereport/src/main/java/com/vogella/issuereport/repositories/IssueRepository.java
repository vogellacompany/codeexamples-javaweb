package com.vogella.issuereport.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.vogella.issuereport.IssueReport;

public interface IssueRepository extends CrudRepository<IssueReport, Long>{

	
	List<IssueReport> findAllByEmail(String email);
	
	
	@Query(value = "SELECT * FROM issues WHERE marked_As_Private = false;", nativeQuery = true)
	List<IssueReport> findAllButPrivate();
	
}
