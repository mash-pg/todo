package com.example.todo.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo.domain.entity.Todo;
import com.example.todo.domain.repository.TodoRepository;

@Service
public class ListTodosUseCase {
	
	private final TodoRepository todoRepository;
	
	public ListTodosUseCase(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	public List<Todo> execute(){
		return todoRepository.findAll();
	}

}
