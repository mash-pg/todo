package com.example.todo.presentation.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.todo.application.usecase.CompleteTodoUseCase;
import com.example.todo.application.usecase.CreateTodoUseCase;
import com.example.todo.application.usecase.DeleteTodoUseCase;
import com.example.todo.application.usecase.GetTodoUseCase;
import com.example.todo.application.usecase.ListTodosUseCase;
import com.example.todo.application.usecase.UpdateTodoUseCase;
import com.example.todo.domain.entity.Todo;
import com.example.todo.domain.valueobject.TodoTitle;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateTodoUseCase createTodoUseCase;

    @MockitoBean
    private ListTodosUseCase listTodosUseCase;

    @MockitoBean
    private GetTodoUseCase getTodoUseCase;

    @MockitoBean
    private UpdateTodoUseCase updateTodoUseCase;

    @MockitoBean
    private DeleteTodoUseCase deleteTodoUseCase;

    @MockitoBean
    private CompleteTodoUseCase completeTodoUseCase;
    // 他の UseCase も @MockBean で定義

    @Test
    @DisplayName("POST /api/todos - 正常に作成できる")
    void 作成成功() throws Exception {
        // Arrange
        Todo todo = new Todo(new TodoTitle("テスト"));
        when(createTodoUseCase.execute("テスト")).thenReturn(todo);

        // Act & Assert
        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"テスト\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title").value("テスト"));
    }

    @Test
    @DisplayName("POST /api/todos - 空タイトルは 400 エラー")
    void 空タイトルは400() throws Exception {
        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"\"}"))
            .andExpect(status().isBadRequest());
    }
}
