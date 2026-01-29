package com.example.todo.domain.repository;

import java.util.List;
import java.util.Optional;

import com.example.todo.domain.entity.Todo;
import com.example.todo.domain.valueobject.TodoId;

public interface TodoRepository {
	//保存（新規/更新両方）
	Todo save(Todo todo);
	
	//ID検索
	Optional<Todo> findById(TodoId id);
	
	//全件取得
	List<Todo> findAll();
	
	//削除
	void deleteById(TodoId id);
}
