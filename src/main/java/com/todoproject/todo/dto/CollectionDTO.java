package com.todoproject.todo.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.todoproject.todo.model.Collection;

public record CollectionDTO(
        UUID id,
        String collectionName,
        String collectionColor,
        List<TodoDTO> todos,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CollectionDTO fromEntity(Collection collection) {
        return new CollectionDTO(
                collection.getId(),
                collection.getCollectionName(),
                collection.getCollectionColor(),
                collection.getTodos() != null
                        ? collection.getTodos().stream().map(TodoDTO::fromEntity).toList()
                        : List.of(),
                collection.getCreatedAt(),
                collection.getUpdatedAt()
        );
    }
}
