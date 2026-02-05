package com.todoproject.todo.service;

import com.todoproject.todo.model.Todo;
import com.todoproject.todo.model.Collection;
import com.todoproject.todo.repository.CollectionRepository;
import com.todoproject.todo.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final CollectionRepository collectionRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository, CollectionRepository collectionRepository) {
        this.todoRepository = todoRepository;
        this.collectionRepository = collectionRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo createtodo(Todo todo, UUID collectionId) {
        Collection collection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new EntityNotFoundException("Collection not found"));

        todo.setCollection(collection);
        return todoRepository.save(todo);
    }

    public Todo toggleTodoCompletion(UUID id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo not found"));

        todo.setCompleted(!todo.getCompleted());
        return todoRepository.save(todo);
    }

    public List<Todo> getTodoByTitle(String title) {
        return todoRepository.findTodosByTitle(title);
    }

    public void deleteTodo(UUID id) {
        todoRepository.deleteById(id);
    }
}

