package com.example.todo.presentation.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.application.usecase.CompleteTodoUseCase;
import com.example.todo.application.usecase.CreateTodoUseCase;
import com.example.todo.application.usecase.DeleteTodoUseCase;
import com.example.todo.application.usecase.GetTodoUseCase;
import com.example.todo.application.usecase.ListTodosUseCase;
import com.example.todo.application.usecase.UpdateTodoUseCase;
import com.example.todo.domain.entity.Todo;
import com.example.todo.presentation.dto.CreateTodoRequest;
import com.example.todo.presentation.dto.TodoResponse;
import com.example.todo.presentation.dto.UpdateTodoRequest;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/todos")
@Slf4j
public class TodoController {

    private final GetTodoUseCase getTodoUseCase;
	private final CreateTodoUseCase createtodousecase;
	private final ListTodosUseCase listtodousecase;
	private final UpdateTodoUseCase updateTodoUseCase;
	private final DeleteTodoUseCase deleteTodoUseCase;
	private final CompleteTodoUseCase completeTodoUseCase;
	
	public TodoController(CreateTodoUseCase createtodousecase,
			ListTodosUseCase listtodousecase,
			GetTodoUseCase getTodoUseCase,
			UpdateTodoUseCase updateTodoUseCase,
			DeleteTodoUseCase deleteTodoUseCase,
			CompleteTodoUseCase completeTodoUseCase) {
		this.createtodousecase = createtodousecase;
		this.listtodousecase = listtodousecase;
		this.getTodoUseCase = getTodoUseCase;
		this.updateTodoUseCase = updateTodoUseCase;
		this.deleteTodoUseCase = deleteTodoUseCase;
		this.completeTodoUseCase =  completeTodoUseCase;
	}
	
    // POST /api/todos
    @PostMapping
    public ResponseEntity<TodoResponse> create(@Valid @RequestBody CreateTodoRequest request) {
    	log.info(request.getDescription());
        // 1. UseCase を呼ぶ
    	Todo todo = createtodousecase.execute(request.getTitle());
        // 2. Entity を DTO に変換
    	TodoResponse response = TodoResponse.from(todo);
        // 3. 201 Created で返す
    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET /api/todos
    @GetMapping
    public ResponseEntity<List<TodoResponse>> list(
    		@RequestParam(defaultValue="createdAt") String sort
    		) {
        // 1. UseCase を呼ぶ
    	List<Todo> todos = listtodousecase.execute(sort);
        // 2. Entity のリストを DTO のリストに変換
    	List<TodoResponse> responses = todos.stream()
    			.map(TodoResponse::from) //ここでEntityをDTOに変換
    			.collect(Collectors.toList());
        // 3. 200 OK で返す
    	return ResponseEntity.ok(responses);
    }
    
    // GET /api/todos/{id} - 詳細取得 
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> get(@PathVariable Long id){
    	Todo todo = getTodoUseCase.execute(id);
    	return ResponseEntity.ok(TodoResponse.from(todo));
    }
    // PUT /api/todos/{id} - 更新
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> update(
    		@PathVariable Long id,
    		@Valid @RequestBody UpdateTodoRequest request
    		){
    	Todo todo = updateTodoUseCase.execute(id, request.getTitle());
    	return ResponseEntity.ok(TodoResponse.from(todo));
    }
    // DELETE /api/todos/{id} - 削除
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
    	deleteTodoUseCase.execute(id);
    	return ResponseEntity.noContent().build();//204 No Content
    }
    
    // POST /api/todos/{id}/complete - 完了
    @PostMapping("/{id}/complete")
    public ResponseEntity<TodoResponse> complete(@PathVariable Long id){
    	Todo todo = completeTodoUseCase.execute(id);
    	return ResponseEntity.ok(TodoResponse.from(todo));
    }
}
