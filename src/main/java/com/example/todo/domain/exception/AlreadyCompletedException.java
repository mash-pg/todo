package com.example.todo.domain.exception;

public class AlreadyCompletedException extends RuntimeException{
	public AlreadyCompletedException(String message) {
		super(message);
	}
}
