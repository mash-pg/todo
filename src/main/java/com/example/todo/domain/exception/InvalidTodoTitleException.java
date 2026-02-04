package com.example.todo.domain.exception;

public class InvalidTodoTitleException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidTodoTitleException(String message) {
		super(message);
	}
}
