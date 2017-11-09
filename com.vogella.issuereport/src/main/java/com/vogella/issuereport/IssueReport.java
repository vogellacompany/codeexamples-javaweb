package com.vogella.issuereport;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="issues")
@Entity
public class IssueReport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String email;
	private String url;
	private String description;
	private boolean markedAsPrivate;
	private boolean updates;
	private boolean done;
	private Date created;
	private Date updated;
	
	public IssueReport() {}
	
	
	@PrePersist
	void created() {
		this.created = this.updated = new Date();
	}
	
}
