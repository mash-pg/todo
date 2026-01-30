package com.example.todo.domain.strategy;

import java.util.List;

import com.example.todo.domain.entity.Todo;

public interface SortStrategy {
	List<Todo> sort(List<Todo> todos);
}
