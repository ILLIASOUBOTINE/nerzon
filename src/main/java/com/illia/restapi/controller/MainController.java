package com.illia.restapi.controller;


import com.illia.restapi.dto.CatDTO;
import com.illia.restapi.entity.Cat;
import com.illia.restapi.repository.CatRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "cats_method")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MainController {

    private final CatRepository catRepository;

    @GetMapping("/all")
    public List<Cat> getAll() {
        return catRepository.findAll();
    }

    @Operation(
            summary = "создает нового котика",
            description = "Собиарет DTO котика и билдером собирает и сохраняет сущность в бд"
    )

    @PostMapping("/add")
    public Cat add(@RequestBody CatDTO catDTO) {
        return catRepository.save(
                Cat.builder()
                        .name(catDTO.getName())
                        .age(catDTO.getAge())
                        .weight(catDTO.getWeight())
                        .build());
    }

    @GetMapping()
    public ResponseEntity<Cat> getById(@RequestParam Integer id) {
        return catRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cat with id " + id + " not found"));
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) {
        catRepository.deleteById(id);
        log.info("Cat delete : {}", id);
    }

    @PutMapping("/update")
    public String update(@RequestBody Cat cat) {
       if(!catRepository.existsById(cat.getId())) {
           return "Cat does not exist";
       }

        return catRepository.save(cat).toString();
    }

}
