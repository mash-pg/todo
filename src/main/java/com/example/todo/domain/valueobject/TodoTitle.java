package com.example.todo.domain.valueobject;

import com.example.todo.domain.exception.InvalidTodoTitleException;

public class TodoTitle {
	
	private static final int MAX_LENGTH = 100;
	private final String value;
	
	public TodoTitle(String value) {
		// バリデーション: null・空文字チェック
		if (value == null || value.isEmpty()) {
			throw new InvalidTodoTitleException("タイトルは必須です");
		}
		// バリデーション: 最大文字数チェック
		if (value.length() > MAX_LENGTH) {
			throw new InvalidTodoTitleException("タイトルは" + MAX_LENGTH + "文字以内で入力してください");
		}
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
