package com.example.todo.domain.exception;

public class InvalidTodoTitleException extends RuntimeException{
	public InvalidTodoTitleException(String message) {
		super(message);
	}
}
