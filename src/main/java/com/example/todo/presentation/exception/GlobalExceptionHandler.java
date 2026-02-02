package com.example.todo.presentation.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.todo.domain.exception.AlreadyCompletedException;
import com.example.todo.domain.exception.TodoNotFoundException;
import com.example.todo.presentation.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	

		@ExceptionHandler(TodoNotFoundException.class)
		public ResponseEntity<ErrorResponse> handleNotFound(TodoNotFoundException e){
			ErrorResponse error = new ErrorResponse("Not Found",e.getMessage());
			log.error("Exception occurred : {}",e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}

		@ExceptionHandler(AlreadyCompletedException.class)
		public ResponseEntity<ErrorResponse> handleConflict(AlreadyCompletedException e){
			ErrorResponse error = new ErrorResponse("Conflict",e.getMessage());
			log.error("Exception occurred : {}",e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
		}
		
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e){
			
          String message = e.getBindingResult().getFieldErrors().stream()
                  .map(error -> error.getField() + ": " + error.getDefaultMessage())
                  .collect(Collectors.joining(", "));
          	ErrorResponse error = new ErrorResponse("Bad Request", message);
			log.error("Exception occurred : {}",e.getMessage());
			return ResponseEntity.badRequest().body(error);		

		}

}
