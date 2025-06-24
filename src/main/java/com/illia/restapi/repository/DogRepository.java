package com.illia.restapi.repository;


import com.illia.restapi.entity.Dog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends MongoRepository<Dog, ObjectId> {
    Dog findByName(String name);
}
