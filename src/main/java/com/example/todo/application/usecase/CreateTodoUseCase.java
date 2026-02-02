package com.example.todo.application.usecase;

import org.springframework.stereotype.Service;

import com.example.todo.domain.entity.Todo;
import com.example.todo.domain.factory.TodoFactory;
import com.example.todo.domain.repository.TodoRepository;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CreateTodoUseCase {
	private final TodoRepository todoRepository;
	private final TodoFactory todofactory;
	
	//private static final Logger log = LoggerFactory.getLogger(CreateTodoUseCase.class);
			
	public CreateTodoUseCase(TodoRepository todorepository,TodoFactory todofactory){
		this.todoRepository = todorepository;
		this.todofactory = todofactory;
	}
	
	public Todo execute(String title) {
		Todo todo = todofactory.create(title);
		Todo savedTodo =  todoRepository.save(todo);
		log.info("Todo created : {} , title : {}",savedTodo.getId().getValue(),title);
		return savedTodo;
	}
}
