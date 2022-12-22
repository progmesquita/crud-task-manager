package com.example.taskmanager.dto;

import java.io.Serializable;
import java.time.Instant;

import com.example.taskmanager.entities.Task;

public class TaskDTO implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private Long id;	
	private String title;
	private String description;
	private Instant dueDate;
	
	public TaskDTO() {
	}
	
	public TaskDTO(Long id, String title, String description, Instant dueDate) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
	}
	
	public TaskDTO(Task entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.description = entity.getDescription();
		this.dueDate = entity.getDueDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getDueDate() {
		return dueDate;
	}

	public void setDueDate(Instant dueDate) {
		this.dueDate = dueDate;
	}
}
