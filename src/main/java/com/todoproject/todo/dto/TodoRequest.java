package com.todoproject.todo.dto;

import java.time.LocalDate;
import java.util.UUID;

public record TodoRequest(
        String title,
        String description,
        LocalDate dueDate,
        UUID collectionId
) {}