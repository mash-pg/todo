package com.example.todo.application.usecase;

import org.springframework.stereotype.Service;

import com.example.todo.domain.entity.Todo;
import com.example.todo.domain.exception.TodoNotFoundException;
import com.example.todo.domain.repository.TodoRepository;
import com.example.todo.domain.valueobject.TodoId;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CompleteTodoUseCase {
	private final TodoRepository todoRepository;
	public CompleteTodoUseCase(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	public Todo execute(Long id) {
		TodoId todoId = new TodoId(id);
		Todo todo = todoRepository.findById(todoId).orElseThrow(()->new TodoNotFoundException("Todo not found: " + id));
		todo.complete();
		return todoRepository.save(todo);
		
	}
}
