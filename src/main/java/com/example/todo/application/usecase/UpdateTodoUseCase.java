package com.example.todo.application.usecase;

import org.springframework.stereotype.Service;

import com.example.todo.domain.entity.Todo;
import com.example.todo.domain.exception.TodoNotFoundException;
import com.example.todo.domain.repository.TodoRepository;
import com.example.todo.domain.valueobject.TodoId;
import com.example.todo.domain.valueobject.TodoTitle;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UpdateTodoUseCase {
	
	private final TodoRepository todoRepository;
	
	public UpdateTodoUseCase(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	public Todo execute(Long id,String newTitle) {
		//１．IDで既存のTodoを取得
		TodoId todoId = new TodoId(id);
		Todo todo = todoRepository.findById(todoId).orElseThrow(()->new TodoNotFoundException("Todo not found: " + id));
		//２．タイトルを変更（Entityのメソッドをよぶ）
		TodoTitle title = new TodoTitle(newTitle);
		todo.changeTitle(title);
		//３．保存して返す
		return todoRepository.save(todo);
	}
}
