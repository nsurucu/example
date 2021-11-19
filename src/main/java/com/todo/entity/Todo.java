package com.todo.entity;

import lombok.Data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Todo {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotEmpty(message = "Title is required")
	private String title;

	@NotNull(message = "Target date is required")
	private Date targetDate;
	
	private String username;
	
	private boolean isCompleted;
	
	protected Todo() {
		
	}
	
	public Todo(String title, Date targetDate, String username) {
		super();
		this.title = title;
		this.targetDate = targetDate;
		this.username = username;
		this.isCompleted = false;
	}


	
}
