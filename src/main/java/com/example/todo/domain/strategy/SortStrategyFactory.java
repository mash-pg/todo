package com.example.todo.domain.strategy;

import org.springframework.stereotype.Component;

@Component
public class SortStrategyFactory {
	private final CreatedAtSortStrategy createdAtSort;
	private final PrioritySortStrategy prioritySort;
	private final DueDateSortStrategy dueDateSort;
	
	public SortStrategyFactory(CreatedAtSortStrategy createdAtSort,
			PrioritySortStrategy prioritySort,
			DueDateSortStrategy dueDateSort) {
		this.createdAtSort = createdAtSort;
		this.prioritySort = prioritySort;
		this.dueDateSort = dueDateSort;
	}
	
	public SortStrategy create(String sortType) {
		return switch(sortType) {
			case "priority" -> prioritySort;
			case "dueDate" -> dueDateSort;
			default -> createdAtSort;
		};
	}
	
}
