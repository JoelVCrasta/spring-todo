package com.todoproject.todo.controller;

import com.todoproject.todo.dto.CollectionDTO;
import com.todoproject.todo.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.todoproject.todo.model.Collection;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {
    private CollectionService collectionService;

    @Autowired
    public void setCollectionService(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping
    public ResponseEntity<List<CollectionDTO>> getAll() {
        List<CollectionDTO> collections = collectionService.findAllCollections().stream().map(CollectionDTO::fromEntity).toList();
        return ResponseEntity.ok(collections);
    }

    @PostMapping
    public ResponseEntity<CollectionDTO> create(@RequestBody Collection collection) {
        Collection newCollection = collectionService.createCollection(collection);
        return ResponseEntity.status(HttpStatus.CREATED).body(CollectionDTO.fromEntity(newCollection));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        collectionService.deleteCollectionById(id);
        return ResponseEntity.noContent().build();
    }
}
