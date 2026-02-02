package com.example.todo.domain.application.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.todo.application.usecase.CreateTodoUseCase;
import com.example.todo.domain.entity.Todo;
import com.example.todo.domain.factory.TodoFactory;
import com.example.todo.domain.repository.TodoRepository;
import com.example.todo.domain.valueobject.TodoId;
import com.example.todo.domain.valueobject.TodoPriority;
import com.example.todo.domain.valueobject.TodoStatus;
import com.example.todo.domain.valueobject.TodoTitle;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateTodoUseCaseTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private TodoFactory todoFactory;

    @InjectMocks
    private CreateTodoUseCase useCase;

    @Test
    @DisplayName("ToDo を作成できる")
    void 正常に作成できる() {
    	
        // Arrange（準備）
        String title = "テスト";
        Todo todo = new Todo(new TodoTitle(title));
        Todo savedTodo = new Todo(
	                new TodoId(1L),           // ← ID を設定！
	                new TodoTitle(title),
	                TodoStatus.OPEN,
	                TodoPriority.LOW,
	                null,                      // description
	                null,                      // dueDate
	                LocalDateTime.now(),       // createdAt
	                null                       // updatedAt
        		);
        
        
        // モックの振る舞いを定義
        when(todoFactory.create(title)).thenReturn(todo);
        when(todoRepository.save(todo)).thenReturn(savedTodo);

        // Act（実行）
        Todo result = useCase.execute(title);

        // Assert（検証）
        assertNotNull(result);
        verify(todoRepository, times(1)).save(todo);  // save が1回呼ばれたか確認
    }	
}
