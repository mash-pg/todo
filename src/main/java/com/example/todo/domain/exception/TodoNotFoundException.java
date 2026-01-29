package com.example.todo.domain.exception;

public class TodoNotFoundException extends RuntimeException{
	public TodoNotFoundException(String message) {
		super(message);
	}
}
