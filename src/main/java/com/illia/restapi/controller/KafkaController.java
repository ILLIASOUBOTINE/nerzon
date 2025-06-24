package com.illia.restapi.controller;


import com.illia.restapi.entity.Cat;
import com.illia.restapi.kafka.KafkaProducer;
import com.illia.restapi.repository.CatRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/kafka")
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true", matchIfMissing = false)
public class KafkaController {

    private final KafkaProducer kafkaProducer;
    private final CatRepository catRepository;

    public KafkaController(KafkaProducer kafkaProducer, CatRepository catRepository) {
        this.kafkaProducer = kafkaProducer;
        this.catRepository = catRepository;
    }

    @GetMapping()
    public ResponseEntity<Cat> getCat(@RequestParam Integer id) {
      return catRepository.findById(id)
              .map(cat -> {
                  kafkaProducer.send("cats_topic",cat.toString());
                  return ResponseEntity.ok().body(cat);
              })
              .orElse(ResponseEntity.notFound().build());
    }
}
