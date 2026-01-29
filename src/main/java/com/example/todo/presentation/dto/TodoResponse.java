package com.example.todo.presentation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.todo.domain.entity.Todo;
import com.example.todo.domain.valueobject.TodoPriority;
import com.example.todo.domain.valueobject.TodoStatus;


public class TodoResponse {
	private Long id;
	private String title;
	private TodoStatus status;
	private TodoPriority priority;
	private String description;
	private LocalDate duedate;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	public static TodoResponse from(Todo todo) {
		TodoResponse response = new TodoResponse();
		response.id = (todo.getId() != null) ? todo.getId().getValue():null;
		response.title = todo.getTitle().getValue();
		response.status = todo.getStatus();
		response.priority = todo.getPriority();
		response.description = todo.getDescription();
		response.duedate = todo.getDueDate();
		response.created_at = todo.getCreatedAt();
		response.updated_at = todo.getUpdatedAt();
		
		return response;
	}

	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public TodoStatus getStatus() {
		return status;
	}
	
	public TodoPriority getPriority() {
		return priority;
	}
	
	public String getDescription() {
		return description;
	}
	public LocalDate getDueDate() {
		return duedate;
	}
	public LocalDateTime getCreatedAt() {
		return created_at;
	}	
	public LocalDateTime getUpdatedAt() {
		return updated_at;
	}
	

	
}
