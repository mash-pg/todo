package com.example.todo.domain.exception;

public class AlreadyCompletedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AlreadyCompletedException(String message) {
		super(message);
	}
}
