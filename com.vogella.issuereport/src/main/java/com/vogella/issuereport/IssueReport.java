package com.vogella.issuereport;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Table(name="issues")
@Entity
public class IssueReport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JsonIgnore
	private String email;
	private String url;
	private String description;
	@JsonIgnore
	private boolean markedAsPrivate;
	@JsonIgnore
	private boolean updates;
	private boolean done;
	private Date created;
	private Date updated;
	
	public IssueReport() {}
	
	public IssueReport(String email, String url, String desc, boolean markedAsPrivate, boolean updates) {
		this.email = email;
		this.url = url;
		this.description = desc;
		this.markedAsPrivate = markedAsPrivate;
		this.updates = updates;
	}
	
	@PrePersist
	void created() {
		this.created = this.updated = new Date();
	}
	
}
