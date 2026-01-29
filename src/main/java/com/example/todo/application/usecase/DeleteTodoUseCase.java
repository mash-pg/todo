package com.example.todo.application.usecase;

import org.springframework.stereotype.Service;

import com.example.todo.domain.repository.TodoRepository;
import com.example.todo.domain.valueobject.TodoId;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DeleteTodoUseCase {
	private final TodoRepository todoRepository;
	
	public DeleteTodoUseCase(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	public void execute(Long id) {
		TodoId todoId = new TodoId(id);
		todoRepository.deleteById(todoId);
	}
}
