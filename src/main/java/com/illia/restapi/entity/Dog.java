package com.illia.restapi.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;



@Getter
@Setter
@Document(collection = "dogs")
@NoArgsConstructor
@AllArgsConstructor
public class Dog {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String name;

    private Integer age;

    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
