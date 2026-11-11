package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CreateCategoryUseCaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void before() {
        categoryRepository.deleteAll();
    }

    @Test
    public void should_create_category() throws Exception {
        String requestBody = """
                {
                    "name": "Nubank",
                    "type": "EXPENSE"
                }
                """;

        mockMvc.perform(post("/finance/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Nubank"))
                .andExpect(jsonPath("$.type").value("EXPENSE"));

        assertThat(categoryRepository.count()).isEqualTo(1);
    }

    @AfterEach
    void after() {
        categoryRepository.deleteAll();
    }
}