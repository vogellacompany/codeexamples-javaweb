package com.vogella;

import java.util.Date;

public interface ITodo {

	long getId();

	String getSummary();

	void setSummary(String summary);

	boolean isDone();

	void setDone(boolean isDone);

	Date getDueDate();

	void setDueDate(Date dueDate);

	ITodo copy();

	void setDescription(String description);

	String getDescription();

}