package com.example.todo.domain.factory;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.todo.domain.entity.Todo;
import com.example.todo.domain.valueobject.TodoTitle;

@Component
public class TodoFactory {
	
	public Todo create(String title) {
		
		//title の前後空白を除去（trim）してから TodoTitle に渡す
		//TodoTitleを生成（validationはVOが担当）
		String titletrim = title.trim();
		TodoTitle todoTitle = new TodoTitle(titletrim);
		
		//コンストラクタでtitle設定
		Todo todo = new Todo(todoTitle);
		
		//createdAt に現在日時を自動設定
		LocalDateTime createAt = LocalDateTime.now();
		todo.changeCreatedAt(createAt);
		
		return todo;
	}
}
