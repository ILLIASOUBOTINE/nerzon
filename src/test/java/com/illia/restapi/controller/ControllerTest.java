package com.illia.restapi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.restapi.dto.CatDTO;
import com.illia.restapi.repository.CatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CatRepository catRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        catRepository.deleteAll();
    }

    @Test
    void addCatIntegrationTest() throws Exception {

        CatDTO catDTO = new CatDTO();
        catDTO.setName("Pushok");
        catDTO.setAge(3);
        catDTO.setWeight(5);

        mockMvc.perform(post("/api/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(catDTO)))
                .andExpect(status().isOk());

        assertThat(catRepository.findAll())
                .hasSize(1)
                .first()
                .satisfies(cat -> {
                    assertThat(cat.getName()).isEqualTo(catDTO.getName());
                    assertThat(cat.getAge()).isEqualTo(catDTO.getAge());
                    assertThat(cat.getWeight()).isEqualTo(catDTO.getWeight());
                });

    }

}
