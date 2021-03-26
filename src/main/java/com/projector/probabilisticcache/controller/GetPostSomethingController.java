package com.projector.probabilisticcache.controller;

import com.projector.probabilisticcache.cache.LruCache;
import com.projector.probabilisticcache.model.IdEntity;
import com.projector.probabilisticcache.repository.IdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GetPostSomethingController {

    private static final LruCache cache = new LruCache(50);

    @Autowired
    private IdRepository idRepository;

    @GetMapping("fire")
    public Iterable<IdEntity> getAll() {
        return idRepository.findAll();
    }

    @GetMapping("/fire/{id}")
    public Optional<IdEntity> get(@PathVariable Integer id) {
        IdEntity idEntity = cache.get(id);
        if (idEntity == null) {
            Optional<IdEntity> entityOptional = idRepository.findById(id);
            if (entityOptional.isPresent()) {
                idEntity = entityOptional.get();
                cache.put(idEntity.getId(), idEntity);
            } else {
                return Optional.empty();
            }
        }
        return Optional.of(idEntity);
    }

    @PostMapping("/fire")
    public ResponseEntity<IdEntity> post(@RequestParam String value) {
        return ResponseEntity.of(Optional.of(idRepository.save(new IdEntity(value))));
    }

    @GetMapping("/test-cache/{size}")
    public String testCache(@PathVariable Integer size) {
        LruCache cache = new LruCache(10);
        cache.put(1, new IdEntity(""));
        for (int i = 1; i < size; i++) {
            cache.get(1);
        }
        return "OK";
    }
}
