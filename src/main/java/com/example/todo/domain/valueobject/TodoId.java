package com.example.todo.domain.valueobject;

public class TodoId {
	private final Long id;
	public TodoId(Long id){
		if(id == null) {
			throw new IllegalArgumentException("IDは必須です");
		}
		this.id = id;
	}
	
	public Long getValue() {
		return id;
	}
}
