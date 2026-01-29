package com.example.todo.presentation.dto;

import java.time.LocalDate;

import com.example.todo.domain.valueobject.TodoPriority;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateTodoRequest {
	@NotBlank(message = "タイトルは必須です")
	@Size(max = 100,message = "タイトルは１００文字以内です")
	private String title;
	
	private String description;
	private TodoPriority priority;
	private LocalDate duedate;
	
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public TodoPriority getPriority() {
		return priority;
	}
	public LocalDate getDueDate() {
		return duedate;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPriority(TodoPriority priority) {
		this.priority = priority;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDueDate(LocalDate duedate) {
		this.duedate = duedate;
	}
}
