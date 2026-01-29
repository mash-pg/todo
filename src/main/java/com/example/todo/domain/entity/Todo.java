package com.example.todo.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.todo.domain.exception.AlreadyCompletedException;
import com.example.todo.domain.valueobject.TodoId;
import com.example.todo.domain.valueobject.TodoPriority;
import com.example.todo.domain.valueobject.TodoStatus;
import com.example.todo.domain.valueobject.TodoTitle;

public class Todo {
	private final TodoId id;
	private TodoTitle title;
	private TodoStatus status;
	private TodoPriority priority;
	private String description;
	private LocalDate duedate;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	//コンストラクタ：新規作成用（IDなし、ステータスはOPEN）
	public Todo(TodoTitle title) {
		this.id = null;
		this.title = title;
		this.status = TodoStatus.OPEN;
		this.priority = TodoPriority.LOW;
		this.description = null;
		this.duedate = null;
		this.created_at = null;
		this.updated_at = null;
	}
	
	//コンストラクタ：DBから復元用（IDあり）
	public Todo(TodoId id,TodoTitle title,TodoStatus status,TodoPriority priority,
			String description,LocalDate duedate,LocalDateTime created_at,LocalDateTime updated_at) {
		this.id = id;
		this.title = title;
		this.status = status;
		this.priority = priority;
		this.description = description;
		this.duedate = duedate;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public void complete() {
		if(this.status == TodoStatus.DONE) {
			throw new AlreadyCompletedException("すでに完了しています");
		}
		this.status = TodoStatus.DONE;
	}
	
	public TodoId getId() {
		return id;
	}
	
	public TodoTitle getTitle() {
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
	//引数のデータ更新やな
	public void changeTitle(TodoTitle title) {
		this.title = title;
	}

		
}
