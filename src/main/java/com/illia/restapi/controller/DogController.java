package com.illia.restapi.controller;


import com.illia.restapi.entity.Dog;
import com.illia.restapi.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/dog")
public class DogController {

    private final DogRepository dogRepository;

    @GetMapping("/all")
    public List<Dog> getAll() {
        return dogRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Dog> add(@RequestBody Dog dog) {
        Dog savedDog = dogRepository.save(dog);
        return ResponseEntity.ok(savedDog);
    }

    @GetMapping("/name")
    public ResponseEntity<Dog> getByName(@RequestParam String name) {
        Dog savedDog = dogRepository.findByName(name);
        if (savedDog != null) {
            return ResponseEntity.ok(savedDog);
        }
        return ResponseEntity.notFound().build();
    }

}
