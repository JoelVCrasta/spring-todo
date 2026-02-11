package com.todoproject.todo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import com.todoproject.todo.model.Todo;

public record TodoDTO (
    UUID id,
    String title,
    String description,
    Boolean completed,
    LocalDate dueDate,
    UUID collectionId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static TodoDTO fromEntity(Todo todo) {
        return new TodoDTO(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getCompleted(),
                todo.getDueDate(),
                todo.getCollection() != null ? todo.getCollection().getId() : null,
                todo.getCreatedAt(),
                todo.getUpdatedAt()
        );
    }
}


