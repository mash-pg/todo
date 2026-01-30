package com.example.todo.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo.domain.entity.Todo;
import com.example.todo.domain.repository.TodoRepository;
import com.example.todo.domain.strategy.SortStrategy;
import com.example.todo.domain.strategy.SortStrategyFactory;

@Service
public class ListTodosUseCase {
	
	private final TodoRepository todoRepository;
	private final SortStrategyFactory sortStrategy;
	
	public ListTodosUseCase(TodoRepository todoRepository ,SortStrategyFactory sortStrategy) {
		this.todoRepository = todoRepository;
		this.sortStrategy =  sortStrategy;
	}
	
	public List<Todo> execute(String sortType){
		List<Todo> todos = todoRepository.findAll();
		
		SortStrategy strategy = sortStrategy.create(sortType);
		return strategy.sort(todos);
	}

}
