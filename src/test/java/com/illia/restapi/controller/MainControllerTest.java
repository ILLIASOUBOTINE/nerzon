package com.illia.restapi.controller;

import com.illia.restapi.entity.Cat;
import com.illia.restapi.repository.CatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainControllerTest {

    @Mock
    private CatRepository catRepository;

    @InjectMocks
    private MainController mainController;

    @Test
    void updateCatFailedTest() {
        int id = 1;
        Cat cat = new Cat();
        cat.setId(id);

        when(catRepository.existsById(cat.getId())).thenReturn(false);

        String expected = "Cat does not exist";

        assertEquals(expected,mainController.update(cat));
    }

    @Test
    void updateCatSucsessTest() {
        int id = 1;
        Cat cat = new Cat();
        cat.setId(id);
        cat.setName("Cat Name");
        cat.setAge(10);
        cat.setWeight(20);

        when(catRepository.existsById(cat.getId())).thenReturn(true);
        when(catRepository.save(cat)).thenReturn(cat);

        assertEquals(cat.toString(), mainController.update(cat));
    }
}