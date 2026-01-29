package com.example.todo.application.usecase;

import org.springframework.stereotype.Service;

import com.example.todo.domain.entity.Todo;
import com.example.todo.domain.repository.TodoRepository;
import com.example.todo.domain.valueobject.TodoTitle;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CreateTodoUseCase {
	private final TodoRepository todoRepository;
	
	public CreateTodoUseCase(TodoRepository todorepository){
		this.todoRepository = todorepository;
	}
	
	public Todo execute(String title) {
		//TodoTitleを生成（validationはVOが担当）
		TodoTitle todoTitle = new TodoTitle(title);
		//Todoを生成
		Todo todo = new Todo(todoTitle);
		//Repositoryで保存
		Todo savedTodo = todoRepository.save(todo);
		//保存結果を返す
		return savedTodo;
	}
}
