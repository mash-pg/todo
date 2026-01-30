package com.example.todo.application.usecase;

import org.springframework.stereotype.Service;

import com.example.todo.domain.entity.Todo;
import com.example.todo.domain.factory.TodoFactory;
import com.example.todo.domain.repository.TodoRepository;
import com.example.todo.domain.valueobject.TodoTitle;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CreateTodoUseCase {
	private final TodoRepository todoRepository;
	private final TodoFactory todofactory;
	
	public CreateTodoUseCase(TodoRepository todorepository,TodoFactory todofactory){
		this.todoRepository = todorepository;
		this.todofactory = todofactory;
	}
	
	public Todo execute(String title) {
		Todo todo = todofactory.create(title);
		return todoRepository.save(todo);
	}
}
