package com.todoproject.todo.controller;

import com.todoproject.todo.model.Todo;
import com.todoproject.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // get all todos or todos by its title
    @GetMapping
    public List<Todo> getTodos(
            @RequestParam(required = false) String title
    ) {
        if (title != null) {
            return todoService.getTodoByTitle(title);
        }

        return todoService.getAllTodos();
    }

    @PostMapping("/{collectionId}")
    public ResponseEntity<Todo> createTodo(
            @RequestBody Todo todo,
            @PathVariable UUID collectionId
    ) {
        Todo newTodo = todoService.createtodo(todo, collectionId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<Todo> toggleCompleted(@PathVariable UUID id) {
        Todo updatedTodo = todoService.toggleTodoCompletion(id);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable UUID id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
