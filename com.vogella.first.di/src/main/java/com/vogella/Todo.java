package com.vogella;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Todo implements ITodo {

	public final long id;
	
	private String summary;

	private String description;

	private Boolean done;

	private Date dueDate;

	public Todo() {
		this(-1);
	}

	public Todo(long i) {
		this(i, "");
	}

	@Autowired
	public Todo(long i, @Qualifier("summary") String summary) {
		this.id = i;
		this.summary = summary;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getSummary() {
		return summary;
	}

	@Override
	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Autowired
	@Qualifier("description")
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean isDone() {
		return done;
	}

	@Autowired
	@Override
	public void setDone(boolean isDone) {
		this.done = isDone;
	}

	@Override
	public Date getDueDate() {
		return dueDate;
	}

	@Autowired
	@Override
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", summary=" + summary + ", description=" + description + "]";
	}

	@Override
	public Todo copy() {
		Todo todo = new Todo(id, summary);
		todo.setDone(isDone());
		todo.setDueDate(getDueDate());
		todo.setDescription(getDescription());

		return todo;
	}
}
