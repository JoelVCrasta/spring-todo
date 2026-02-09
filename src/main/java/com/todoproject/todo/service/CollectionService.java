package com.todoproject.todo.service;

import com.todoproject.todo.model.Collection;
import com.todoproject.todo.repository.CollectionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CollectionService {
    private final CollectionRepository collectionRepository;

    @Autowired
    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public Collection createCollection(Collection collection) {
        return collectionRepository.saveAndFlush(collection);
    }

    public void deleteCollectionById(UUID id) {
        if (!collectionRepository.existsById(id)) {
            throw new EntityNotFoundException("Collection not found");
        }
        collectionRepository.deleteById(id);
    }

    public List<Collection> findAllCollections() {
        return collectionRepository.findAll();
    }
}
