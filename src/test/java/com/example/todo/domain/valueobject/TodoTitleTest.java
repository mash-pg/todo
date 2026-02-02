package com.example.todo.domain.valueobject;
import org.junit.jupiter.api.Test;

import com.example.todo.domain.exception.InvalidTodoTitleException;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class TodoTitleTest {
/**
	- 空文字は不可 → `InvalidTodoTitleException`
	- 100文字を超えると不可 → `InvalidTodoTitleException`
	- 1〜100文字は有効
 * */
    @Test
    @DisplayName("空文字は例外が発生する")
    void 空文字は例外() {
        assertThrows(InvalidTodoTitleException.class,
            () -> new TodoTitle(""));
    }
    @Test
    @DisplayName("1文字のタイトルは正常に作成できる")
    void 最小文字数で作成できる() {
        // Arrange（準備）
        String input = "a";

        // Act（実行）
        TodoTitle title = new TodoTitle(input);

        // Assert（検証）
        assertEquals("a", title.getValue());
    }
    
    @Test
    @DisplayName("100文字で作成できる")
    void 通常文字数以内で作成できる() {
			String input100 = "a".repeat(100);
			TodoTitle title = new TodoTitle(input100);
			assertEquals(input100, title.getValue());

    }
    
    @Test
    @DisplayName("101文字は例外")
    void 上限の文字数例外() {
			String content = "a".repeat(101);
			assertThrows(InvalidTodoTitleException.class,
	                () -> new TodoTitle(content));
    }
}