package com.example.todo.domain.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.todo.domain.entity.Todo;

@Component
public class CreatedAtSortStrategy implements SortStrategy{
	@Override
	public List<Todo> sort(List<Todo> todos) {
		List<Todo> sorted = new ArrayList<>(todos);
		sorted.sort(Comparator.comparing(todo->todo.getCreatedAt()));
		return sorted;
	}
}
